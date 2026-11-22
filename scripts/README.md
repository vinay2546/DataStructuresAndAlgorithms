# Java README and Progress Tracker

The repository has two Java utilities:

- ProblemTracker.java normalizes problem README metadata and regenerates hierarchical progress trackers.
- ReadmeResetter.java intentionally resets every existing README.md.

## Normal Maven build

The progress tracker runs automatically during Maven's process-test-classes phase:

```bash
mvn clean package
```

It updates problem README metadata and managed progress sections without erasing manually written content outside generated sections.

## One-time hard reset of every README

To replace every existing README.md—including the root README, category and directory documentation, problem READMEs, and manually written guides—with standard path-aware templates, run:

```bash
mvn -DresetReadmes=true clean package
```

**Warning:** this intentionally discards all existing README content. Before changing any file, the resetter backs up every README to a timestamped sibling directory named like DataStructuresAndAlgorithms-readme-backup-YYYYMMDD-HHMMSS-SSS. Keep that backup until you have reviewed the reset.

The resetter:
- Resets every existing README.md under the repository root.
- Uses a problem template for LeetCode problem folders, a standard section template for other directory READMEs, and a project template for the root README.
- Resets problem statuses to Not Started; old statuses and hand-written notes are not retained.
- Preserves each README's existing newline convention (CRLF or LF).
- Skips Git internals and Maven's target directory.
- Runs in Maven's process-classes phase, after compilation and before the normal progress tracker (process-test-classes).

The reset is opt-in. A normal mvn clean package never activates it. The resetter only changes local files; it does not commit or push anything.

## Review changes

After the build, inspect the result:

```bash
git status
git diff
```
