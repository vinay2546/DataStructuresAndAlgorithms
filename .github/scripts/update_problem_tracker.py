#!/usr/bin/env python3
"""Generate recursive progress trackers for every directory containing LeetCode problem leaves."""
from __future__ import annotations

import re
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
ROOT_README = ROOT / "README.md"
START = "<!-- PROBLEM_TRACKER:START -->"
END = "<!-- PROBLEM_TRACKER:END -->"

# A problem is a leaf directory whose complete name follows this convention:
# AA_Problem_Description_LT123, AB_Another_Problem_LT456, etc.
PROBLEM_DIR = re.compile(r"^[A-Z]+_.+_LT(\d+)$")
STATUS_LINE = re.compile(r"^\s*\*\*Status:\*\*\s*(.*?)\s*$", re.IGNORECASE)
STATUS_ICON = {"Solved": "🟩", "In Progress": "🟨", "Not Started": "⬜"}


def read_problem(folder: Path) -> dict:
    readme = folder / "README.md"
    if not readme.is_file():
        return {"folder": folder, "title": folder.name, "status": "Not Started"}
    content = readme.read_text(encoding="utf-8")
    title_match = re.search(r"^#\s+(.+?)\s*$", content, re.MULTILINE)
    status_match = STATUS_LINE.search(content)
    title = title_match.group(1).strip() if title_match else folder.name
    value = status_match.group(1).strip().casefold() if status_match else ""
    status = {
        "solved": "Solved",
        "in progress": "In Progress",
        "not started": "Not Started",
    }.get(value, "Not Started")
    return {"folder": folder, "title": title, "status": status}


def safe(value: str) -> str:
    return value.replace("|", r"\|").replace("\n", " ").strip()


def stats(items: list[dict]) -> dict:
    result = {"Total": len(items), "Solved": 0, "In Progress": 0, "Not Started": 0}
    for item in items:
        result[item["status"]] += 1
    return result


def pct(counts: dict) -> str:
    return f"{counts['Solved'] / counts['Total'] * 100:.1f}%" if counts["Total"] else "0.0%"


def relative_link(readme: Path, target: Path) -> str:
    return Path(__import__("os").path.relpath(target, readme.parent)).as_posix()


def problem_table(items: list[dict], readme: Path) -> list[str]:
    lines = ["| Problem | LeetCode # | Status |", "|---|---:|---|"]
    for item in sorted(items, key=lambda x: x["folder"].name.casefold()):
        match = PROBLEM_DIR.fullmatch(item["folder"].name)
        number = match.group(1) if match else "—"
        target = item["folder"] / "README.md"
        if not target.is_file():
            target = item["folder"] / "Solution.java"
        if not target.is_file():
            target = item["folder"]
        link = relative_link(readme, target)
        lines.append(
            f"| [{safe(item['title'])}]({link}) | #{number} | "
            f"{STATUS_ICON[item['status']]} {item['status']} |"
        )
    return lines


def replace_generated_section(path: Path, generated: str) -> bool:
    current = path.read_text(encoding="utf-8") if path.exists() else f"# {path.parent.name}\n"
    if START in current and END in current:
        before, tail = current.split(START, 1)
        _, after = tail.split(END, 1)
        updated = f"{before}{START}\n{generated}\n{END}{after}"
    else:
        # Never replace or truncate user-authored README content. Add a managed
        # section when this directory first becomes part of the hierarchy.
        updated = current.rstrip() + f"\n\n{START}\n{generated}\n{END}\n"
    if updated == current:
        return False
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(updated, encoding="utf-8")
    return True


def discover_problem_leaves() -> list[dict]:
    problems = []
    # Search the complete repository, pruning Git internals.
    for current, directories, _files in __import__("os").walk(ROOT):
        directories[:] = [name for name in directories if name != ".git"]
        current_path = Path(current)
        for name in directories:
            folder = current_path / name
            if PROBLEM_DIR.fullmatch(name):
                problems.append(read_problem(folder))
    return sorted(problems, key=lambda item: item["folder"].as_posix().casefold())


def build_directory_index(problems: list[dict]) -> dict[Path, list[dict]]:
    """Map every ancestor directory to all problem leaves beneath it."""
    descendants: dict[Path, list[dict]] = defaultdict(list)
    for problem in problems:
        parent = problem["folder"].parent
        while parent == ROOT or ROOT in parent.parents:
            descendants[parent].append(problem)
            if parent == ROOT:
                break
            parent = parent.parent
    return descendants


def directory_section(directory: Path, items: list[dict], descendants: dict[Path, list[dict]]) -> str:
    readme = directory / "README.md"
    counts = stats(items)
    lines = [
        f"## Progress Overview — {directory.name}",
        "",
        f"**{counts['Solved']} / {counts['Total']} problem placements solved ({pct(counts)})**",
        "",
        f"🟩 Solved: {counts['Solved']} · 🟨 In Progress: {counts['In Progress']} · "
        f"⬜ Not Started: {counts['Not Started']}",
        "",
        "This tracker is generated recursively. Update the **Status** field in an individual problem README.",
        "",
    ]

    direct_problems = [item for item in items if item["folder"].parent == directory]
    child_directories = sorted(
        child for child in directory.iterdir()
        if child.is_dir() and child in descendants and descendants[child]
    ) if directory.exists() else []

    if child_directories:
        lines += [
            "### Subdirectories",
            "",
            "| Directory | Total | Solved | In Progress | Not Started | Completion |",
            "|---|---:|---:|---:|---:|---:|",
        ]
        for child in child_directories:
            child_counts = stats(descendants[child])
            link = relative_link(readme, child / "README.md")
            label = safe(child.name)
            lines.append(
                f"| [{label}]({link}) | {child_counts['Total']} | {child_counts['Solved']} | "
                f"{child_counts['In Progress']} | {child_counts['Not Started']} | {pct(child_counts)} |"
            )
        lines.append("")

    if direct_problems:
        lines += ["### Problems in this directory", "", *problem_table(direct_problems, readme)]

    lines += [
        "",
        "<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>",
    ]
    return "\n".join(lines)


def root_section(top_level: list[Path], descendants: dict[Path, list[dict]]) -> str:
    all_items = descendants.get(ROOT, [])
    counts = stats(all_items)
    lines = [
        "## DSA Progress Overview",
        "",
        f"**{counts['Solved']} / {counts['Total']} problem placements solved ({pct(counts)})**",
        "",
        f"🟩 Solved: {counts['Solved']} · 🟨 In Progress: {counts['In Progress']} · "
        f"⬜ Not Started: {counts['Not Started']}",
        "",
        "| Top-level Directory | Total | Solved | In Progress | Not Started | Completion |",
        "|---|---:|---:|---:|---:|---:|",
    ]
    for directory in top_level:
        items = descendants.get(directory, [])
        if not items:
            continue
        child_counts = stats(items)
        lines.append(
            f"| [{safe(directory.name)}](src/main/java/{directory.name}/README.md) | "
            f"{child_counts['Total']} | {child_counts['Solved']} | {child_counts['In Progress']} | "
            f"{child_counts['Not Started']} | {pct(child_counts)} |"
        )
    lines += [
        "",
        "<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>",
        "",
        "The individual problem README is the source of truth. Allowed statuses: Not Started, In Progress, Solved. "
        "Missing READMEs, legacy Placeholder values, and unknown statuses count as Not Started.",
    ]
    return "\n".join(lines)


def main() -> None:
    problems = discover_problem_leaves()
    descendants = build_directory_index(problems)
    updated = 0

    # Every ancestor of every problem leaf anywhere in the repository receives a tracker.
    directories = sorted(
        (path for path in descendants if path != ROOT),
        key=lambda path: (len(path.parts), path.as_posix().casefold()),
    )
    for directory in directories:
        updated += replace_generated_section(
            directory / "README.md",
            directory_section(directory, descendants[directory], descendants),
        )

    top_level = sorted(
        (path for path in ROOT.iterdir() if path.is_dir() and path in descendants),
        key=lambda path: path.name.casefold(),
    )
    updated += replace_generated_section(ROOT_README, root_section(top_level, descendants))

    print(
        f"Scanned {len(problems)} problem leaves across {len(directories)} "
        f"hierarchy directories; updated {updated} tracker README files."
    )


if __name__ == "__main__":
    main()
