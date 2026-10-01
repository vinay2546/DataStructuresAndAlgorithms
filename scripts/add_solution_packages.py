from pathlib import Path
import re

ROOT = Path("src/main/java")
PACKAGE_RE = re.compile(r"^package\s+[A-Za-z_$][\w$]*(?:\.[A-Za-z_$][\w$]*)*\s*;\s*\n?", re.MULTILINE)

changed = 0
for path in ROOT.rglob("Solution.java"):
    relative = path.relative_to(ROOT)
    package_name = ".".join(relative.parent.parts)
    content = path.read_text(encoding="utf-8")
    declaration = f"package {package_name};\n\n"
    updated = PACKAGE_RE.sub("", content, count=1)
    updated = declaration + updated
    if updated != content:
        path.write_text(updated, encoding="utf-8")
        changed += 1

print(f"Updated {changed} Solution.java files.")
