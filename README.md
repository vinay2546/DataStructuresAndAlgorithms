# Data Structures and Algorithms

Java-based solutions for Data Structures and Algorithms, organized by problem-solving pattern and data structure.

## Maven Structure

```
DataStructuresAndAlgorithms/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       ├── A_Two_Pointers/
    │       ├── F_HashMap/
    │       ├── H_Recursion/
    │       ├── I_BackTracking/
    │       ├── J_Tree/
    │       └── N_Heaps/
    └── test/
        └── java/
```

## Package Naming Convention

The package naming follows the structure used by the reference repository.

### Pattern-based problems

```
src/main/java/<Pattern>/<Problem>_<DifficultyOrId>/
```

Example:

```
src/main/java/A_Two_Pointers/N_3Sum/
    Solution.java
```

Package declaration:

```java
package A_Two_Pointers.N_3Sum;
```

### Concepts and grouped problems

For data structures with concepts or categories:

```
src/main/java/J_Tree/Concepts/<Concept>/
src/main/java/J_Tree/Problems/<Problem>_<Id>/
```

Example:

```java
package J_Tree.Concepts.B_Implementation;
```

## Problem Folder Convention

Each problem can contain:

- `README.md` — problem statement, approach, complexity and notes
- `Solution.java` — primary solution
- Additional solution classes when comparing approaches

Example:

```
A_Two_Pointers/
└── N_3Sum/
    ├── README.md
    └── Solution.java
```

## Build

Compile the project with:

```bash
mvn clean compile
```

Run tests with:

```bash
mvn test
```

## Java Version

Java 17 is used as the baseline.
