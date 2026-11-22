# Local README and Progress Tracker

This folder contains the Python utility used to standardize problem READMEs and regenerate the hierarchical progress trackers.

## Requirements

- Python 3.10 or newer
- No third-party Python packages

## Run locally

Open a terminal at the repository root and run:

```bash
python scripts/update_problem_tracker.py
```

On Windows, if `python` is not available as a command, use:

```powershell
py scripts/update_problem_tracker.py
```

The script locates the repository root from its own file path, so it can also be invoked from another working directory.

## What it updates

- Standardizes each problem README using the repository's problem README template.
- Preserves existing problem-specific explanations and moves unclassified sections under **Additional Notes**.
- Regenerates the managed progress-tracker sections in the root and relevant directory README files.
- Leaves content outside the generated tracker markers untouched.

## Review the changes

After running the script, inspect the changes before committing:

```bash
git status
git diff
```

The script only changes local files. It does not create commits, push changes, contact GitHub, or run GitHub Actions.
