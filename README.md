# Data Structures and Algorithms

Java-based solutions for Data Structures and Algorithms, organized first by **data structure**, then by problem-solving pattern or concept.

## Maven Structure

```
DataStructuresAndAlgorithms/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            ├── Array/
            ├── LinkedList/
            ├── Stack/
            ├── Queue/
            ├── HashMap/
            ├── HashSet/
            ├── Tree/
            ├── Heap/
            ├── Graph/
            ├── Trie/
            ├── String/
            └── Matrix/
```

## Package Naming Convention

The **first package layer is always the data structure**.

```
<DataStructure>/<PatternOrCategory>/<Problem>_<Id>/
```

For example:

```
src/main/java/
└── Tree/
    └── BFS/
        └── InvertBinaryTree_226/
            ├── README.md
            └── Solution.java
```

Package declaration:

```java
package Tree.BFS.InvertBinaryTree_226;
```

### Pattern-based organization

When a problem is associated with a recognizable problem-solving pattern:

```
Array/
└── TwoPointers/
    └── ThreeSum_15/

LinkedList/
└── TwoPointers/
    └── MiddleOfTheLinkedList_876/

Tree/
└── BFS/
    └── BinaryTreeLevelOrderTraversal_102/
```

### Concepts

For learning a data structure itself:

```
Tree/
└── Concepts/
    └── Traversal/
        ├── README.md
        └── ...
```

### Problems without a specific pattern

Use:

```
<DataStructure>/Problems/<Problem>_<Id>/
```

Example:

```
Array/
└── Problems/
    └── BestTimeToBuyAndSellStock_121/
```

## Problem Folder Convention

Each problem folder can contain:

- `README.md` — problem statement, approach, pattern, and complexity
- `Solution.java` — primary solution
- Additional solution classes when comparing approaches

Example:

```
Tree/
└── BFS/
    └── InvertBinaryTree_226/
        ├── README.md
        └── Solution.java
```

## Naming Rules

- **Data structure:** PascalCase — `Array`, `LinkedList`, `Tree`, `Graph`
- **Pattern/category:** PascalCase — `TwoPointers`, `BFS`, `DFS`
- **Problem:** descriptive PascalCase name followed by LeetCode ID — `InvertBinaryTree_226`
- **Java class:** `Solution` unless the problem requires a specific class name
- **One problem per package**

## Build

Compile:

```bash
mvn clean compile
```

Run tests:

```bash
mvn test
```

## Java Version

Java 17 is the baseline.
