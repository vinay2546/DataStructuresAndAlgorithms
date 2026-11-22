# Standard Problem README Template

Every problem-folder README follows this section order:

1. Problem title and LeetCode number
2. Data Structure, Pattern, LeetCode, and Status metadata
3. Problem
4. Approach
5. Algorithm
6. Complexity
7. Solution
8. Additional Notes (only when needed for existing material that does not fit the standard sections)

Use this template for new problem folders:

\`\`\`md
# Problem Name — LeetCode Number

**Data Structure:** Array  
**Pattern:** Fundamentals  
**LeetCode:** #1  
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
\`\`\`

## Rules

- Keep the four metadata fields in this exact order.
- Allowed statuses: \`Not Started\`, \`In Progress\`, and \`Solved\`.
- Keep the standard section order even when a section contains only a placeholder.
- Preserve useful problem-specific explanations. Put other existing sections under **Additional Notes** rather than deleting them.
- Link the Java implementation using the relative \`Solution.java\` link.
- To standardize problem READMEs and regenerate progress trackers, run `python scripts/update_problem_tracker.py` from the repository root. See [`scripts/README.md`](../scripts/README.md) for local instructions.
