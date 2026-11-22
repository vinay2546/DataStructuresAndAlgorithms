package com.vinay2546.tracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Normalizes problem READMEs and regenerates the repository's recursive
 * Markdown progress trackers. Uses only the Java standard library.
 */
public final class ProblemTracker {
    private static final String START = "<!-- PROBLEM_TRACKER:START -->";
    private static final String END = "<!-- PROBLEM_TRACKER:END -->";
    private static final Pattern PROBLEM_DIR = Pattern.compile("^[A-Z]+_.+_LT(\\d+)$");
    private static final Pattern STATUS_LINE = Pattern.compile(
            "^[ \\t]*\\*\\*Status:\\*\\*[ \\t]*(.*?)[ \\t]*$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Pattern DATA_STRUCTURE_LINE = Pattern.compile(
            "^[ \\t]*\\*\\*Data Structure:\\*\\*[ \\t]*(.*?)[ \\t]*$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Pattern PATTERN_LINE = Pattern.compile(
            "^[ \\t]*\\*\\*Pattern:\\*\\*[ \\t]*(.*?)[ \\t]*$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Pattern RESERVED_LINE = Pattern.compile(
            "^[ \\t]*This folder is reserved for the Java solution and problem notes\\.[ \\t]*$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Pattern METADATA_LINE = Pattern.compile(
            "^[ \\t]*\\*\\*(?:Data Structure|Pattern|LeetCode|Status):\\*\\*.*(?:\\n|$)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Pattern HEADING = Pattern.compile("^#\\s+(.+?)\\s*$", Pattern.MULTILINE);
    private static final Pattern SECTION = Pattern.compile("^##\\s+(.+?)\\s*$", Pattern.MULTILINE);
    private static final Map<String, String> STATUS_ICONS = Map.of(
            "Solved", "🟩", "In Progress", "🟨", "Not Started", "⬜");
    private static final Map<String, String> SECTION_ALIASES = Map.of(
            "problem", "Problem",
            "problem statement", "Problem",
            "approach", "Approach",
            "algorithm", "Algorithm",
            "complexity", "Complexity",
            "time and space complexity", "Complexity",
            "solution", "Solution");

    private final Path root;
    private final Path rootReadme;

    private ProblemTracker(Path root) {
        this.root = root.toAbsolutePath().normalize();
        this.rootReadme = this.root.resolve("README.md");
    }

    public static void main(String[] args) throws IOException {
        Path root = args.length > 0 ? Path.of(args[0]) : Path.of(".");
        new ProblemTracker(root).run();
    }

    private void run() throws IOException {
        int normalized = normalizeProblemReadmes();
        List<Problem> problems = discoverProblems();
        Map<Path, List<Problem>> descendants = buildDirectoryIndex(problems);
        int updated = 0;

        List<Path> directories = descendants.keySet().stream()
                .filter(path -> !path.equals(root))
                .sorted(Comparator.comparingInt(Path::getNameCount)
                        .thenComparing(path -> path.toString().toLowerCase(Locale.ROOT)))
                .toList();

        for (Path directory : directories) {
            updated += replaceGeneratedSection(directory.resolve("README.md"),
                    directorySection(directory, descendants.get(directory), descendants)) ? 1 : 0;
        }

        Path sourceRoot = root.resolve("src/main/java");
        List<Path> topLevel = new ArrayList<>();
        if (Files.isDirectory(sourceRoot)) {
            try (Stream<Path> children = Files.list(sourceRoot)) {
                topLevel = children.filter(Files::isDirectory)
                        .filter(descendants::containsKey)
                        .sorted(Comparator.comparing(path -> path.getFileName().toString().toLowerCase(Locale.ROOT)))
                        .toList();
            }
        }
        updated += replaceGeneratedSection(rootReadme, rootSection(topLevel, descendants)) ? 1 : 0;

        System.out.printf("Normalized %d problem READMEs; scanned %d problem leaves across %d hierarchy directories; updated %d tracker README files.%n",
                normalized, problems.size(), directories.size(), updated);
    }

    private int normalizeProblemReadmes() throws IOException {
        final int[] updated = {0};
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs) {
                if (!directory.equals(root) && directory.getFileName().toString().equals(".git")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                if (PROBLEM_DIR.matcher(directory.getFileName().toString()).matches()) {
                    try {
                        if (normalizeProblemReadme(directory)) updated[0]++;
                    } catch (IOException exception) {
                        throw new TrackerIOException(exception);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return updated[0];
    }

    private boolean normalizeProblemReadme(Path folder) throws IOException {
        Path readme = folder.resolve("README.md");
        String original = Files.isRegularFile(readme) ? Files.readString(readme, StandardCharsets.UTF_8) : "";
        Matcher numberMatcher = PROBLEM_DIR.matcher(folder.getFileName().toString());
        String number = numberMatcher.matches() ? numberMatcher.group(1) : "";

        Matcher titleMatcher = HEADING.matcher(original);
        String heading;
        if (titleMatcher.find()) {
            heading = "# " + titleMatcher.group(1).strip();
        } else {
            String problemName = folder.getFileName().toString()
                    .replaceFirst("^[A-Z]+_", "")
                    .replaceFirst("_LT\\d+$", "")
                    .replaceAll("(?<=[a-z0-9])(?=[A-Z])", " ")
                    .replace('_', ' ');
            heading = ("# " + problemName + " — " + number).replaceFirst("[ \\u2014]+$", "");
        }

        String dataStructure = firstGroup(DATA_STRUCTURE_LINE, original);
        if (dataStructure.isBlank()) {
            Path javaRoot = root.resolve("src/main/java");
            if (folder.startsWith(javaRoot) && folder.getNameCount() > javaRoot.getNameCount()) {
                dataStructure = folder.getName(javaRoot.getNameCount()).toString();
            } else {
                Path parent = folder.getParent();
                dataStructure = parent == null ? "Fundamentals" : parent.getFileName().toString();
            }
        }

        String pattern = firstGroup(PATTERN_LINE, original);
        if (pattern.isBlank()) {
            String parentName = folder.getParent() == null ? "" : folder.getParent().getFileName().toString();
            pattern = parentName.replaceFirst("^[A-Z]+_", "").replace('_', ' ').strip();
            if (pattern.isBlank()) pattern = "Fundamentals";
        }

        String rawStatus = firstGroup(STATUS_LINE, original).toLowerCase(Locale.ROOT);
        String status = switch (rawStatus) {
            case "solved" -> "Solved";
            case "in progress" -> "In Progress";
            case "not started", "placeholder" -> "Not Started";
            default -> "";
        };
        if (status.isBlank()) {
            Matcher oldStatus = Pattern.compile(
                    "^##\\s+Status\\s*$([\\s\\S]*?)(?=^##\\s+|\\z)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(original);
            status = oldStatus.find() && Pattern.compile("\\[[xX]\\]").matcher(oldStatus.group(1)).find()
                    ? "Solved" : "Not Started";
        }

        String body = METADATA_LINE.matcher(original).replaceAll("");
        body = RESERVED_LINE.matcher(body).replaceAll("");
        body = body.lines().filter(line -> !line.matches("^#\\s+.*")).collect(Collectors.joining("\n")).strip();

        Matcher sectionMatcher = SECTION.matcher(body);
        List<SectionMatch> sectionMatches = new ArrayList<>();
        while (sectionMatcher.find()) {
            sectionMatches.add(new SectionMatch(sectionMatcher.group(1).strip(), sectionMatcher.start(), sectionMatcher.end()));
        }

        String preamble = sectionMatches.isEmpty() ? body : body.substring(0, sectionMatches.get(0).start()).strip();
        Map<String, List<String>> sections = new LinkedHashMap<>();
        List<ExtraSection> extras = new ArrayList<>();
        for (int i = 0; i < sectionMatches.size(); i++) {
            SectionMatch match = sectionMatches.get(i);
            int end = i + 1 < sectionMatches.size() ? sectionMatches.get(i + 1).start() : body.length();
            String content = body.substring(match.end(), end).strip();
            String canonical = SECTION_ALIASES.get(match.title().toLowerCase(Locale.ROOT));
            if (canonical != null) {
                if (!content.isEmpty()) sections.computeIfAbsent(canonical, ignored -> new ArrayList<>()).add(content);
            } else if (!content.isEmpty()) {
                extras.add(new ExtraSection(match.title(), content));
            }
        }
        if (!preamble.isEmpty()) sections.computeIfAbsent("Problem", ignored -> new ArrayList<>()).add(0, preamble);

        String problem = combined(sections, "Problem", "Add the problem statement or LeetCode link here.");
        String approach = combined(sections, "Approach", "Add a brief explanation of the chosen approach.");
        String algorithm = combined(sections, "Algorithm", "1. Add the solution steps.");
        String complexity = combined(sections, "Complexity", "- **Time:** Add time complexity.\n- **Space:** Add space complexity.");
        String solution = combined(sections, "Solution", "See [Solution.java](./Solution.java).");
        if (!solution.contains("Solution.java")) solution += "\n\nSee [Solution.java](./Solution.java).";

        List<String> lines = new ArrayList<>(List.of(
                heading, "",
                "**Data Structure:** " + dataStructure + "  ",
                "**Pattern:** " + pattern + "  ",
                "**LeetCode:** #" + number + "  ",
                "**Status:** " + status, "",
                "## Problem", "", problem, "",
                "## Approach", "", approach, "",
                "## Algorithm", "", algorithm, "",
                "## Complexity", "", complexity, "",
                "## Solution", "", solution));
        if (!extras.isEmpty()) {
            lines.addAll(List.of("", "## Additional Notes", ""));
            for (ExtraSection extra : extras) {
                lines.add("### " + extra.title());
                lines.add("");
                lines.add(extra.content());
                lines.add("");
            }
            while (!lines.isEmpty() && lines.get(lines.size() - 1).isEmpty()) lines.remove(lines.size() - 1);
        }
        String updated = String.join("\n", lines).stripTrailing() + "\n";
        updated = withLineEndings(updated, lineEndingOf(original));
        if (updated.equals(original)) return false;
        Files.createDirectories(folder);
        Files.writeString(readme, updated, StandardCharsets.UTF_8);
        return true;
    }

    private static String firstGroup(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1).strip() : "";
    }

    private static String combined(Map<String, List<String>> sections, String name, String fallback) {
        List<String> values = sections.getOrDefault(name, List.of());
        String result = String.join("\n\n", values).strip();
        return result.isEmpty() ? fallback : result;
    }

    private List<Problem> discoverProblems() throws IOException {
        List<Problem> problems = new ArrayList<>();
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs) {
                if (!directory.equals(root) && directory.getFileName().toString().equals(".git")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                if (PROBLEM_DIR.matcher(directory.getFileName().toString()).matches()) {
                    try {
                        problems.add(readProblem(directory));
                    } catch (IOException exception) {
                        throw new TrackerIOException(exception);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        problems.sort(Comparator.comparing(problem -> problem.folder().toString().toLowerCase(Locale.ROOT)));
        return problems;
    }

    private Problem readProblem(Path folder) throws IOException {
        Path readme = folder.resolve("README.md");
        if (!Files.isRegularFile(readme)) return new Problem(folder, folder.getFileName().toString(), "Not Started");
        String content = Files.readString(readme, StandardCharsets.UTF_8);
        String title = firstGroup(HEADING, content);
        if (title.isBlank()) title = folder.getFileName().toString();
        String rawStatus = firstGroup(STATUS_LINE, content).toLowerCase(Locale.ROOT);
        String status = switch (rawStatus) {
            case "solved" -> "Solved";
            case "in progress" -> "In Progress";
            case "not started" -> "Not Started";
            default -> "Not Started";
        };
        return new Problem(folder, title, status);
    }

    private Map<Path, List<Problem>> buildDirectoryIndex(List<Problem> problems) {
        Map<Path, List<Problem>> descendants = new HashMap<>();
        for (Problem problem : problems) {
            Path parent = problem.folder().getParent();
            while (parent != null && (parent.equals(root) || parent.startsWith(root))) {
                descendants.computeIfAbsent(parent, ignored -> new ArrayList<>()).add(problem);
                if (parent.equals(root)) break;
                parent = parent.getParent();
            }
        }
        return descendants;
    }

    private String directorySection(Path directory, List<Problem> items, Map<Path, List<Problem>> descendants)
            throws IOException {
        Path readme = directory.resolve("README.md");
        Counts counts = Counts.from(items);
        List<String> lines = new ArrayList<>(List.of(
                "## Progress Overview — " + directory.getFileName(), "",
                "**" + counts.solved() + " / " + counts.total() + " problem placements solved (" + counts.percentage() + ")**", "",
                "🟩 Solved: " + counts.solved() + " · 🟨 In Progress: " + counts.inProgress()
                        + " · ⬜ Not Started: " + counts.notStarted(), "",
                "This tracker is generated recursively. Update the **Status** field in an individual problem README.", ""));

        List<Problem> directProblems = items.stream()
                .filter(item -> item.folder().getParent().equals(directory)).toList();
        List<Path> childDirectories = new ArrayList<>();
        if (Files.isDirectory(directory)) {
            try (Stream<Path> children = Files.list(directory)) {
                childDirectories = children.filter(Files::isDirectory)
                        .filter(descendants::containsKey)
                        .filter(child -> !descendants.get(child).isEmpty())
                        .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                        .toList();
            }
        }
        if (!childDirectories.isEmpty()) {
            lines.addAll(List.of("### Subdirectories", "",
                    "| Directory | Total | Solved | In Progress | Not Started | Completion |",
                    "|---|---:|---:|---:|---:|---:|"));
            for (Path child : childDirectories) {
                Counts childCounts = Counts.from(descendants.get(child));
                lines.add("| [" + safe(child.getFileName().toString()) + "](" + relativeLink(readme, child.resolve("README.md")) + ") | "
                        + childCounts.total() + " | " + childCounts.solved() + " | " + childCounts.inProgress()
                        + " | " + childCounts.notStarted() + " | " + childCounts.percentage() + " |");
            }
            lines.add("");
        }
        if (!directProblems.isEmpty()) {
            lines.addAll(List.of("### Problems in this directory", "", "| Problem | LeetCode # | Status |", "|---|---:|---|"));
            directProblems.stream()
                    .sorted(Comparator.comparing(item -> item.folder().getFileName().toString().toLowerCase(Locale.ROOT)))
                    .forEach(item -> {
                        Matcher matcher = PROBLEM_DIR.matcher(item.folder().getFileName().toString());
                        String number = matcher.matches() ? matcher.group(1) : "—";
                        Path target = item.folder().resolve("README.md");
                        if (!Files.exists(target)) target = item.folder().resolve("Solution.java");
                        if (!Files.exists(target)) target = item.folder();
                        lines.add("| [" + safe(item.title()) + "](" + relativeLink(readme, target) + ") | #" + number
                                + " | " + STATUS_ICONS.get(item.status()) + " " + item.status() + " |");
                    });
        }
        lines.add("");
        lines.add("<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>");
        return String.join("\n", lines);
    }

    private String rootSection(List<Path> topLevel, Map<Path, List<Problem>> descendants) {
        Counts counts = Counts.from(descendants.getOrDefault(root, List.of()));
        List<String> lines = new ArrayList<>(List.of(
                "## DSA Progress Overview", "",
                "**" + counts.solved() + " / " + counts.total() + " problem placements solved (" + counts.percentage() + ")**", "",
                "🟩 Solved: " + counts.solved() + " · 🟨 In Progress: " + counts.inProgress()
                        + " · ⬜ Not Started: " + counts.notStarted(), "",
                "| Top-level Directory | Total | Solved | In Progress | Not Started | Completion |",
                "|---|---:|---:|---:|---:|---:|"));
        for (Path directory : topLevel) {
            Counts child = Counts.from(descendants.getOrDefault(directory, List.of()));
            if (child.total() == 0) continue;
            lines.add("| [" + safe(directory.getFileName().toString()) + "](src/main/java/"
                    + directory.getFileName() + "/README.md) | " + child.total() + " | " + child.solved()
                    + " | " + child.inProgress() + " | " + child.notStarted() + " | " + child.percentage() + " |");
        }
        lines.addAll(List.of("",
                "<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>", "",
                "The individual problem README is the source of truth. Allowed statuses: Not Started, In Progress, Solved. "
                        + "Missing READMEs, legacy Placeholder values, and unknown statuses count as Not Started."));
        return String.join("\n", lines);
    }

    private boolean replaceGeneratedSection(Path path, String generated) throws IOException {
        String current = Files.isRegularFile(path)
                ? Files.readString(path, StandardCharsets.UTF_8)
                : "# " + path.getParent().getFileName() + "\n";
        String updated;
        int start = current.indexOf(START);
        int end = current.indexOf(END);
        if (start >= 0 && end >= start) {
            updated = current.substring(0, start) + START + "\n" + generated + "\n" + current.substring(end + END.length());
        } else {
            updated = current.stripTrailing() + "\n\n" + START + "\n" + generated + "\n" + END + "\n";
        }
        updated = withLineEndings(updated, lineEndingOf(current));
        if (updated.equals(current)) return false;
        Files.createDirectories(path.getParent());
        Files.writeString(path, updated, StandardCharsets.UTF_8);
        return true;
    }

    /**
     * Detect the newline convention already used by a file.
     * Generated Markdown is assembled with LF, then converted back before writing.
     * This preserves CRLF files on Windows and LF files on Unix or in LF-configured repos.
     */
    private static String lineEndingOf(String content) {
        int firstNewline = content.indexOf('\n');
        if (firstNewline >= 0) {
            return firstNewline > 0 && content.charAt(firstNewline - 1) == '\r' ? "\r\n" : "\n";
        }
        return System.lineSeparator();
    }

    private static String withLineEndings(String content, String lineEnding) {
        return content.replace("\r\n", "\n").replace("\n", lineEnding);
    }

    private String relativeLink(Path readme, Path target) {
        return readme.getParent().relativize(target).toString().replace('\\', '/');
    }

    private static String safe(String value) {
        return value.replace("|", "\\|").replace("\n", " ").strip();
    }

    private record Problem(Path folder, String title, String status) {}
    private record SectionMatch(String title, int start, int end) {}
    private record ExtraSection(String title, String content) {}
    private record Counts(int total, int solved, int inProgress, int notStarted) {
        static Counts from(List<Problem> problems) {
            int solved = 0, inProgress = 0, notStarted = 0;
            for (Problem problem : problems) {
                switch (problem.status()) {
                    case "Solved" -> solved++;
                    case "In Progress" -> inProgress++;
                    default -> notStarted++;
                }
            }
            return new Counts(problems.size(), solved, inProgress, notStarted);
        }
        String percentage() {
            return total == 0 ? "0.0%" : String.format(Locale.ROOT, "%.1f%%", solved * 100.0 / total);
        }
    }

    private static final class TrackerIOException extends RuntimeException {
        TrackerIOException(IOException cause) { super(cause); }
    }
}
