# Java README and Progress Tracker

The README normalizer and hierarchical progress tracker are implemented in Java at `src/main/java/com/vinay2546/tracker/ProblemTracker.java`.

## Automatic execution with Maven

The tracker is bound to Maven's `process-classes` phase. It runs automatically for lifecycle commands that reach that phase, including:

```bash
mvn package
mvn test
mvn verify
mvn install
mvn clean package
```

Run Maven from the repository root. The tracker executes after Java compilation and before tests/package creation. It requires only the JDK already needed by this Java repository; **Python is not required**.

`mvn validate` and `mvn compile` do not reach `process-classes`, so they do not run the tracker.

## What it updates

- Standardizes every problem README using the repository's problem README format.
- Preserves problem-specific explanations and moves unclassified sections under **Additional Notes**.
- Regenerates managed progress-tracker sections in the root and relevant directory README files.
- Leaves content outside the generated tracker markers untouched.
- Counts problem-folder placements, including intentional duplicates, consistently with the existing tracker.

The tracker only changes local files. It does not create commits, push changes, contact GitHub, or run GitHub Actions.

## Review the changes

After a Maven build, inspect the generated changes before committing:

```bash
git status
git diff
```
