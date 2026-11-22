#!/usr/bin/env python3
"""Regenerate the root README problem tracker from per-problem README metadata."""

from __future__ import annotations

import os
import re
import subprocess
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
JAVA_ROOT = ROOT / "src" / "main" / "java"
README = ROOT / "README.md"
START = "<!-- PROBLEM_TRACKER:START -->"
END = "<!-- PROBLEM_TRACKER:END -->"
PROBLEM_DIR = re.compile(r".+_LT\d+$")
STATUS_LINE = re.compile(r"^\s*\*\*Status:\*\*\s*(.*?)\s*$", re.IGNORECASE)
LEGACY_STATUSES = {"placeholder": "Not Started"}


def git_names(base: str, head: str) -> list[str]:
    """Return changed paths for this push, or the latest commit for manual runs."""
    if base and not re.fullmatch(r"0+", base):
        command = ["git", "diff", "--name-only", "--diff-filter=ACMR", base, head]
    else:
        command = ["git", "diff-tree", "--no-commit-id", "--name-only", "-r", head]
    result = subprocess.run(command, cwd=ROOT, check=True, text=True, capture_output=True)
    return [line.strip() for line in result.stdout.splitlines() if line.strip()]


def problem_folders() -> list[Path]:
    if not JAVA_ROOT.exists():
        return []
    return sorted(
        (path for path in JAVA_ROOT.rglob("*") if path.is_dir() and PROBLEM_DIR.fullmatch(path.name)),
        key=lambda path: path.as_posix().lower(),
    )


def read_status(problem_dir: Path) -> tuple[str, str]:
    readme = problem_dir / "README.md"
    if not readme.is_file():
        return "Not Started", problem_dir.name

    content = readme.read_text(encoding="utf-8")
    title_match = re.search(r"^#\s+(.+?)\s*$", content, re.MULTILINE)
    title = title_match.group(1).strip() if title_match else problem_dir.name
    status_match = STATUS_LINE.search(content)
    if not status_match:
        return "Not Started", title

    raw_status = status_match.group(1).strip()
    normalized = raw_status.casefold()
    if normalized in LEGACY_STATUSES:
        return LEGACY_STATUSES[normalized], title
    if normalized == "not started":
        return "Not Started", title
    if normalized == "in progress":
        return "In Progress", title
    if normalized == "solved":
        return "Solved", title

    # Unknown or malformed status values must never be interpreted as solved.
    return "Not Started", title


def markdown_cell(value: str) -> str:
    return value.replace("|", r"\|").replace("\n", " ").strip()


def render_tracker(folders: list[Path], changed_paths: list[str]) -> str:
    totals: dict[str, dict[str, int]] = defaultdict(
        lambda: {"Total": 0, "Solved": 0, "In Progress": 0, "Not Started": 0}
    )
    details: dict[str, tuple[str, str, str]] = {}
    overall = {"Total": 0, "Solved": 0, "In Progress": 0, "Not Started": 0}

    for folder in folders:
        relative = folder.relative_to(JAVA_ROOT)
        data_structure = relative.parts[0] if relative.parts else "Other"
        status, title = read_status(folder)
        totals[data_structure]["Total"] += 1
        totals[data_structure][status] += 1
        overall["Total"] += 1
        overall[status] += 1

        readme = folder / "README.md"
        target = readme if readme.is_file() else folder / "Solution.java"
        if target.is_file():
            link = target.relative_to(ROOT).as_posix()
        else:
            link = folder.relative_to(ROOT).as_posix()
        details[folder.relative_to(ROOT).as_posix()] = (title, status, link)

    completion = overall["Solved"] / overall["Total"] * 100 if overall["Total"] else 0
    lines = [
        "## Problem Progress Tracker",
        "",
        "This section is generated automatically by GitHub Actions from each problem folder's README.md.",
        "Update the **Status:** line in a problem README to Not Started, In Progress, or Solved.",
        "Legacy Placeholder values, missing READMEs, and unrecognized status values are counted as Not Started.",
        "",
        f"**Overall:** {overall['Solved']} / {overall['Total']} solved ({completion:.1f}% complete) · "
        f"{overall['In Progress']} in progress · {overall['Not Started']} not started",
        "",
        "| Data Structure | Total | Solved | In Progress | Not Started | Completion |",
        "|---|---:|---:|---:|---:|---:|",
    ]

    for name in sorted(totals):
        counts = totals[name]
        percent = counts["Solved"] / counts["Total"] * 100 if counts["Total"] else 0
        lines.append(
            f"| [{name}](src/main/java/{name}/README.md) | {counts['Total']} | "
            f"{counts['Solved']} | {counts['In Progress']} | {counts['Not Started']} | {percent:.1f}% |"
        )

    lines.extend(["", "### Recently changed problems", ""])
    changed_problem_dirs: list[Path] = []
    seen: set[str] = set()
    for changed in changed_paths:
        path = Path(changed)
        if path.parts[:3] != ("src", "main", "java"):
            continue
        candidate = None
        for index, part in enumerate(path.parts):
            if PROBLEM_DIR.fullmatch(part):
                candidate = Path(*path.parts[: index + 1])
                break
        if candidate is None:
            continue
        key = candidate.as_posix()
        if key not in seen:
            seen.add(key)
            changed_problem_dirs.append(ROOT / candidate)

    if changed_problem_dirs:
        for folder in changed_problem_dirs[:12]:
            key = folder.relative_to(ROOT).as_posix()
            item = details.get(key)
            if item:
                title, status, link = item
                lines.append(f"- [{markdown_cell(title)}]({link}) — {status}")
        if len(changed_problem_dirs) > 12:
            lines.append(f"- And {len(changed_problem_dirs) - 12} more changed problem folders.")
    else:
        lines.append("- No problem-folder changes were detected in the triggering commit.")

    lines.extend([
        "",
        "<sub>Counts include every directory ending in _LT<number>. Status is never inferred from Java source code.</sub>",
    ])
    return "\n".join(lines)


def main() -> None:
    folders = problem_folders()
    base = os.environ.get("BASE_SHA", "").strip()
    head = os.environ.get("HEAD_SHA", "").strip() or "HEAD"
    changed_paths = git_names(base, head)

    current = README.read_text(encoding="utf-8")
    generated = render_tracker(folders, changed_paths)
    if START in current and END in current:
        before, remainder = current.split(START, 1)
        _, after = remainder.split(END, 1)
        updated = f"{before}{START}\n{generated}\n{END}{after}"
    else:
        anchor = "## Trie learning progression"
        if anchor not in current:
            raise RuntimeError(f"Could not find insertion anchor {anchor!r} in root README.md")
        updated = current.replace(anchor, f"{START}\n{generated}\n{END}\n\n{anchor}", 1)

    if updated != current:
        README.write_text(updated, encoding="utf-8")
        print(f"Updated tracker for {len(folders)} problem folders.")
    else:
        print(f"Tracker already up to date for {len(folders)} problem folders.")


if __name__ == "__main__":
    main()
