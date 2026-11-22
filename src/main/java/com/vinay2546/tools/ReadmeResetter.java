package com.vinay2546.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Resets every existing README.md to a path-aware template after backing it up. */
public final class ReadmeResetter {
    private static final Pattern PROBLEM_DIRECTORY = Pattern.compile("^[A-Z]+_.+_LT(\\d+)$");
    private static final DateTimeFormatter BACKUP_TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private ReadmeResetter() { }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected the repository root as the only argument.");
        }
        Path root = Path.of(args[0]).toAbsolutePath().normalize();
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException("Repository root does not exist: " + root);
        }

        List<Path> readmes = findReadmes(root);
        if (readmes.isEmpty()) {
            System.out.println("No README.md files found. Nothing to reset.");
            return;
        }

        Path backupRoot = root.resolveSibling(root.getFileName()
                + "-readme-backup-" + LocalDateTime.now().format(BACKUP_TIMESTAMP));

        // Finish backing up every README before modifying any of them.
        for (Path readme : readmes) {
            Path backup = backupRoot.resolve(root.relativize(readme));
            Files.createDirectories(backup.getParent());
            Files.copy(readme, backup, StandardCopyOption.COPY_ATTRIBUTES);
        }

        for (Path readme : readmes) {
            String original = Files.readString(readme, StandardCharsets.UTF_8);
            String newline = detectLineEnding(original);
            String normalized = templateFor(root, readme).replace("\n", newline);
            Files.writeString(readme, normalized, StandardCharsets.UTF_8);
        }

        System.out.printf("Reset %d README.md files.%nBackup: %s%n", readmes.size(), backupRoot);
    }

    private static List<Path> findReadmes(Path root) throws IOException {
        List<Path> readmes = new ArrayList<>();
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs) {
                if (!directory.equals(root)) {
                    String name = directory.getFileName().toString();
                    if (name.equals(".git") || name.equals("target")) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (attrs.isRegularFile() && file.getFileName().toString().equals("README.md")) {
                    readmes.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        readmes.sort(Path::compareTo);
        return readmes;
    }

    private static String templateFor(Path root, Path readme) {
        Path directory = readme.getParent();
        if (directory.equals(root)) {
            return """
                    # Data Structures and Algorithms

                    ## Overview

                    A Java-based collection of data structure and algorithm concepts, patterns, and problem solutions.

                    ## Repository Structure

                    Topics are organized by data structure, followed by categories and individual problem folders.

                    ## Build

                    Run Maven clean package from the repository root to compile the solutions and refresh progress trackers.

                    ## Getting Started

                    Build the project with Maven. The hierarchical progress tracker is generated automatically below.
                    """;
        }

        Matcher matcher = PROBLEM_DIRECTORY.matcher(directory.getFileName().toString());
        if (matcher.matches()) {
            return problemTemplate(root, directory, matcher.group(1));
        }

        return """
                # %s

                ## Overview

                Add a concise overview of this section.

                ## Contents

                Add links to the important topics, subdirectories, or implementations.

                ## Notes

                Add learning notes or references relevant to this section.
                """.formatted(humanize(directory.getFileName().toString()));
    }

    private static String problemTemplate(Path root, Path directory, String number) {
        String problemName = humanize(directory.getFileName().toString()
                .replaceFirst("^[A-Z]+_", "").replaceFirst("_LT\\d+$", ""));
        String dataStructure = "Fundamentals";
        Path javaRoot = root.resolve("src/main/java");
        if (directory.startsWith(javaRoot) && directory.getNameCount() > javaRoot.getNameCount()) {
            dataStructure = humanize(directory.getName(javaRoot.getNameCount()).toString());
        }
        Path parent = directory.getParent();
        String pattern = parent == null ? "Fundamentals" : humanize(parent.getFileName().toString());

        return """
                # %s — %s

                **Data Structure:** %s  
                **Pattern:** %s  
                **LeetCode:** #%s  
                **Status:** Not Started

                ## Problem

                Add the problem statement or LeetCode link here.

                ## Approach

                Add a brief explanation of the chosen approach.

                ## Algorithm

                1. Add the solution steps.

                ## Complexity

                - **Time:** Add time complexity.
                - **Space:** Add space complexity.

                ## Solution

                See [Solution.java](./Solution.java).
                """.formatted(problemName, number, dataStructure, pattern, number);
    }

    private static String humanize(String value) {
        return value.replaceFirst("^[A-Z]+_", "")
                .replaceAll("(?<=[a-z0-9])(?=[A-Z])", " ")
                .replace('_', ' ').replaceAll("\\s+", " ").strip();
    }

    private static String detectLineEnding(String content) {
        int newline = content.indexOf('\n');
        return newline > 0 && content.charAt(newline - 1) == '\r' ? "\r\n" : "\n";
    }
}
