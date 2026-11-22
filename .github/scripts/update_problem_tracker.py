#!/usr/bin/env python3
"""Generate hierarchical progress trackers from each problem README status."""
from __future__ import annotations
import os, re, subprocess
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
JAVA_ROOT = ROOT / "src" / "main" / "java"
ROOT_README = ROOT / "README.md"
START, END = "<!-- PROBLEM_TRACKER:START -->", "<!-- PROBLEM_TRACKER:END -->"
PROBLEM_DIR = re.compile(r".+_LT(\d+)$")
STATUS_LINE = re.compile(r"^\s*\*\*Status:\*\*\s*(.*?)\s*$", re.IGNORECASE)
STATUS_ICON = {"Solved": "🟩", "In Progress": "🟨", "Not Started": "⬜"}

def git_names(base, head):
    if base and not re.fullmatch(r"0+", base):
        cmd = ["git", "diff", "--name-only", "--diff-filter=ACMR", base, head]
    else:
        cmd = ["git", "diff-tree", "--no-commit-id", "--name-only", "-r", head]
    out = subprocess.run(cmd, cwd=ROOT, check=True, text=True, capture_output=True)
    return [x.strip() for x in out.stdout.splitlines() if x.strip()]

def read_status(folder):
    readme = folder / "README.md"
    if not readme.is_file():
        return "Not Started", folder.name
    content = readme.read_text(encoding="utf-8")
    match = re.search(r"^#\s+(.+?)\s*$", content, re.MULTILINE)
    title = match.group(1).strip() if match else folder.name
    match = STATUS_LINE.search(content)
    if not match:
        return "Not Started", title
    value = match.group(1).strip().casefold()
    if value == "solved":
        return "Solved", title
    if value == "in progress":
        return "In Progress", title
    return "Not Started", title

def safe(value):
    return value.replace("|", r"\|").replace("\n", " ").strip()

def stats(items):
    result = {"Total": len(items), "Solved": 0, "In Progress": 0, "Not Started": 0}
    for item in items:
        result[item["status"]] += 1
    return result

def pct(counts):
    return f"{counts['Solved'] / counts['Total'] * 100:.1f}%" if counts["Total"] else "0.0%"

def problem_table(items):
    lines = ["| Problem | LeetCode # | Status |", "|---|---:|---|"]
    for item in sorted(items, key=lambda x: x["folder"].name.lower()):
        match = PROBLEM_DIR.fullmatch(item["folder"].name)
        number = match.group(1) if match else "—"
        target = item["folder"] / "README.md"
        if not target.is_file():
            target = item["folder"] / "Solution.java"
        link = target.relative_to(item["folder"].parent).as_posix()
        lines.append(f"| [{safe(item['title'])}]({link}) | #{number} | {STATUS_ICON[item['status']]} {item['status']} |")
    return lines

def replace_section(path, generated, insertion_heading=None, replace_heading=None):
    current = path.read_text(encoding="utf-8") if path.exists() else f"# {path.parent.name}\n"
    if START in current and END in current:
        before, tail = current.split(START, 1)
        _, after = tail.split(END, 1)
        updated = f"{before}{START}\n{generated}\n{END}{after}"
    elif replace_heading and replace_heading in current:
        before, _ = current.split(replace_heading, 1)
        updated = f"{before}{START}\n{generated}\n{END}\n"
    elif insertion_heading and insertion_heading in current:
        updated = current.replace(insertion_heading, f"{START}\n{generated}\n{END}\n\n{insertion_heading}", 1)
    else:
        updated = current.rstrip() + f"\n\n{START}\n{generated}\n{END}\n"
    if updated != current:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(updated, encoding="utf-8")
        return 1
    return 0

def discover():
    by_structure = defaultdict(list)
    by_category = defaultdict(lambda: defaultdict(list))
    for structure_dir in sorted(p for p in JAVA_ROOT.iterdir() if p.is_dir()):
        for category_dir in sorted(p for p in structure_dir.iterdir() if p.is_dir()):
            for folder in sorted(p for p in category_dir.iterdir() if p.is_dir() and PROBLEM_DIR.fullmatch(p.name)):
                status, title = read_status(folder)
                item = {"folder": folder, "title": title, "status": status}
                by_structure[structure_dir.name].append(item)
                by_category[structure_dir.name][category_dir.name].append(item)
    return by_structure, by_category

def category_section(items):
    c = stats(items)
    lines = [
        f"### Progress: {c['Solved']} / {c['Total']} solved ({pct(c)})", "",
        f"🟩 Solved: {c['Solved']} · 🟨 In Progress: {c['In Progress']} · ⬜ Not Started: {c['Not Started']}", "",
        "Change the Status field in an individual problem README to update this tracker.", "",
        *problem_table(items)
    ]
    return "\n".join(lines)

def structure_section(structure, categories):
    all_items = [p for group in categories.values() for p in group]
    c = stats(all_items)
    lines = [
        "## Progress Overview", "",
        f"**{c['Solved']} / {c['Total']} problem placements solved ({pct(c)})**", "",
        f"🟩 Solved: {c['Solved']} · 🟨 In Progress: {c['In Progress']} · ⬜ Not Started: {c['Not Started']}", "",
        "| Category | Total | Solved | In Progress | Not Started | Completion |",
        "|---|---:|---:|---:|---:|---:|"
    ]
    for category, items in sorted(categories.items()):
        s = stats(items)
        label = category[2:].replace("_", " ") if len(category) > 2 else category
        lines.append(f"| [{label}]({category}/README.md) | {s['Total']} | {s['Solved']} | {s['In Progress']} | {s['Not Started']} | {pct(s)} |")
    lines += ["", "<sub>Counts represent package placements. Intentional duplicates across categories are counted in each category.</sub>"]
    return "\n".join(lines)

def root_section(by_structure):
    all_items = [p for group in by_structure.values() for p in group]
    c = stats(all_items)
    lines = [
        "## DSA Progress Overview", "",
        f"**{c['Solved']} / {c['Total']} problem placements solved ({pct(c)})**", "",
        f"🟩 Solved: {c['Solved']} · 🟨 In Progress: {c['In Progress']} · ⬜ Not Started: {c['Not Started']}", "",
        "| Data Structure | Total | Solved | In Progress | Not Started | Completion |",
        "|---|---:|---:|---:|---:|---:|"
    ]
    for name, items in sorted(by_structure.items()):
        s = stats(items)
        lines.append(f"| [{name}](src/main/java/{name}/README.md) | {s['Total']} | {s['Solved']} | {s['In Progress']} | {s['Not Started']} | {pct(s)} |")
    lines += [
        "", "<sub>Counts represent package placements, not unique LeetCode IDs. Intentional duplicates are counted in each category.</sub>", "",
        "Update the Status field in an individual problem README using Not Started, In Progress, or Solved. Legacy Placeholder, missing README files, and unknown values count as Not Started."
    ]
    return "\n".join(lines)

def main():
    base = os.environ.get("BASE_SHA", "").strip()
    head = os.environ.get("HEAD_SHA", "").strip() or "HEAD"
    changed = git_names(base, head)
    by_structure, by_category = discover()
    updated = 0
    for structure, categories in by_category.items():
        for category, items in categories.items():
            updated += replace_section(JAVA_ROOT / structure / category / "README.md",
                                       category_section(items), replace_heading="## Problems")
    for structure, categories in by_category.items():
        updated += replace_section(JAVA_ROOT / structure / "README.md",
                                   structure_section(structure, categories), insertion_heading="## Roadmap")
    updated += replace_section(ROOT_README, root_section(by_structure),
                               insertion_heading="## Trie learning progression")
    print(f"Scanned {sum(map(len, by_structure.values()))} problem folders, {len(by_structure)} data structures, {sum(len(x) for x in by_category.values())} categories.")
    print(f"Updated {updated} tracker sections; {len(changed)} paths changed in triggering push.")

if __name__ == "__main__":
    main()
