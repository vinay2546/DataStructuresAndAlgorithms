# README and Progress Tracker

The repository uses `scripts/update_problem_tracker.py` to standardize problem READMEs and regenerate the hierarchical progress trackers.

## Automatic execution with Maven

The tracker is bound to Maven's `validate` phase. It runs automatically before compilation whenever you execute a lifecycle phase that includes `validate`, for example:

```bash
mvn validate
mvn test
mvn package
mvn clean package
```

On Windows, Maven uses the Python launcher (`py`). On macOS/Linux, it uses `python3` by default. Python 3.10 or newer is required; no third-party Python packages are needed.

If Python is installed under a different executable name or location, override it:

```bash
mvn package -Dtracker.python.executable=/path/to/python
```

## Run manually

You can still run the tracker without Maven:

```bash
python scripts/update_problem_tracker.py
```

On Windows:

```powershell
py scripts/update_problem_tracker.py
```

## What it updates

- Standardizes each problem README using the repository's problem README template.
- Preserves existing problem-specific explanations and moves unclassified sections under **Additional Notes**.
- Regenerates the managed progress-tracker sections in the root and relevant directory README files.
- Leaves content outside the generated tracker markers untouched.

The script only changes local files. It does not create commits, push changes, contact GitHub, or run GitHub Actions.

## Review the changes

After a Maven build or manual run, inspect the changes before committing:

```bash
git status
git diff
```
