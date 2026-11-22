# BFS

Breadth-First Search traversal for trees.

## Learn
- Queue-based level-order traversal.
- Level and depth tracking.
- Processing nodes by distance from the root.
- Zigzag, vertical, boundary and view patterns built on BFS.
- When level-by-level state is more natural than recursive DFS.

## Goal
Be able to write the standard tree BFS template and adapt it to level, distance and ordering requirements.

<!-- PROBLEM_TRACKER:START -->
## Progress Overview — AA_BFS

**0 / 39 problem placements solved (0.0%)**

🟩 Solved: 0 · 🟨 In Progress: 0 · ⬜ Not Started: 39

This tracker is generated recursively. Update the **Status** field in an individual problem README.

### Problems in this directory

| Problem | LeetCode # | Status |
|---|---:|---|
| [102. Binary Tree Level Order Traversal](AA_BinaryTreeLevelOrderTraversal_LT102/README.md) | #102 | ⬜ Not Started |
| [107. Binary Tree Level Order Traversal II](AB_BinaryTreeLevelOrderTraversalII_LT107/README.md) | #107 | ⬜ Not Started |
| [429. NAry Tree Level Order Traversal](AC_NAryTreeLevelOrderTraversal_LT429/README.md) | #429 | ⬜ Not Started |
| [103. Binary Tree Zigzag Level Order Traversal](AD_BinaryTreeZigzagLevelOrderTraversal_LT103/README.md) | #103 | ⬜ Not Started |
| [637. Average Of Levels In Binary Tree](AE_AverageOfLevelsInBinaryTree_LT637/README.md) | #637 | ⬜ Not Started |
| [1161. Maximum Level Sum Of ABinary Tree](AF_MaximumLevelSumOfABinaryTree_LT1161/README.md) | #1161 | ⬜ Not Started |
| [515. Find Largest Value In Each Tree Row](AG_FindLargestValueInEachTreeRow_LT515/README.md) | #515 | ⬜ Not Started |
| [1302. Deepest Leaves Sum](AH_DeepestLeavesSum_LT1302/README.md) | #1302 | ⬜ Not Started |
| [2583. Kth Largest Sum In ABinary Tree](AI_KthLargestSumInABinaryTree_LT2583/README.md) | #2583 | ⬜ Not Started |
| [199. Binary Tree Right Side View](AJ_BinaryTreeRightSideView_LT199/README.md) | #199 | ⬜ Not Started |
| [513. Find Bottom Left Tree Value](AK_FindBottomLeftTreeValue_LT513/README.md) | #513 | ⬜ Not Started |
| [993. Cousins In Binary Tree](AL_CousinsInBinaryTree_LT993/README.md) | #993 | ⬜ Not Started |
| [116. Populating Next Right Pointers In Each Node](AM_PopulatingNextRightPointersInEachNode_LT116/README.md) | #116 | ⬜ Not Started |
| [117. Populating Next Right Pointers In Each Node II](AN_PopulatingNextRightPointersInEachNodeII_LT117/README.md) | #117 | ⬜ Not Started |
| [1609. Even Odd Tree](AO_EvenOddTree_LT1609/README.md) | #1609 | ⬜ Not Started |
| [2641. Cousins In Binary Tree II](AP_CousinsInBinaryTreeII_LT2641/README.md) | #2641 | ⬜ Not Started |
| [104. Maximum Depth Of Binary Tree](AQ_MaximumDepthOfBinaryTree_LT104/README.md) | #104 | ⬜ Not Started |
| [111. Minimum Depth Of Binary Tree](AR_MinimumDepthOfBinaryTree_LT111/README.md) | #111 | ⬜ Not Started |
| [559. Maximum Depth Of NAry Tree](AS_MaximumDepthOfNAryTree_LT559/README.md) | #559 | ⬜ Not Started |
| [623. Add One Row To Tree](AT_AddOneRowToTree_LT623/README.md) | #623 | ⬜ Not Started |
| [226. Invert Binary Tree](AU_InvertBinaryTree_LT226/README.md) | #226 | ⬜ Not Started |
| [617. Merge Two Binary Trees](AV_MergeTwoBinaryTrees_LT617/README.md) | #617 | ⬜ Not Started |
| [662. Maximum Width Of Binary Tree](AW_MaximumWidthOfBinaryTree_LT662/README.md) | #662 | ⬜ Not Started |
| [314. Binary Tree Vertical Order Traversal](AX_BinaryTreeVerticalOrderTraversal_LT314/README.md) | #314 | ⬜ Not Started |
| [987. Vertical Order Traversal Of ABinary Tree](AY_VerticalOrderTraversalOfABinaryTree_LT987/README.md) | #987 | ⬜ Not Started |
| [1602. Find Nearest Right Node In Binary Tree](AZ_FindNearestRightNodeInBinaryTree_LT1602/README.md) | #1602 | ⬜ Not Started |
| [1379. Find ACorresponding Node Of ABinary Tree In AClone Of That Tree](BA_FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree_LT1379/README.md) | #1379 | ⬜ Not Started |
| [863. All Nodes Distance KIn Binary Tree](BB_AllNodesDistanceKInBinaryTree_LT863/README.md) | #863 | ⬜ Not Started |
| [2385. Amount Of Time For Binary Tree To Be Infected](BC_AmountOfTimeForBinaryTreeToBeInfected_LT2385/README.md) | #2385 | ⬜ Not Started |
| [865. Smallest Subtree With All The Deepest Nodes](BD_SmallestSubtreeWithAllTheDeepestNodes_LT865/README.md) | #865 | ⬜ Not Started |
| [1123. Lowest Common Ancestor Of Deepest Leaves](BE_LowestCommonAncestorOfDeepestLeaves_LT1123/README.md) | #1123 | ⬜ Not Started |
| [297. Serialize And Deserialize Binary Tree](BF_SerializeAndDeserializeBinaryTree_LT297/README.md) | #297 | ⬜ Not Started |
| [655. Print Binary Tree](BG_PrintBinaryTree_LT655/README.md) | #655 | ⬜ Not Started |
| [919. Complete Binary Tree Inserter](BH_CompleteBinaryTreeInserter_LT919/README.md) | #919 | ⬜ Not Started |
| [1361. Validate Binary Tree Nodes](BI_ValidateBinaryTreeNodes_LT1361/README.md) | #1361 | ⬜ Not Started |
| [1660. Correct ABinary Tree](BJ_CorrectABinaryTree_LT1660/README.md) | #1660 | ⬜ Not Started |
| [2471. Minimum Number Of Operations To Sort ABinary Tree By Level](BK_MinimumNumberOfOperationsToSortABinaryTreeByLevel_LT2471/README.md) | #2471 | ⬜ Not Started |
| [100. Same Tree](BL_SameTree_LT100/README.md) | #100 | ⬜ Not Started |
| [101. Symmetric Tree](BM_SymmetricTree_LT101/README.md) | #101 | ⬜ Not Started |

<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>
<!-- PROBLEM_TRACKER:END -->
