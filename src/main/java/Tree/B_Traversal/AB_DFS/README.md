# DFS

Depth-First Search traversal for trees.

## Learn
- Recursive preorder, inorder and postorder traversal.
- Iterative DFS using a stack.
- Passing state down and returning state up recursion.
- Backtracking and path state.
- When subtree aggregation is more natural than level-order processing.

## Goal
Recognize whether a tree problem can be solved by a simple DFS template or requires additional recursive state.

<!-- PROBLEM_TRACKER:START -->
## Progress Overview — AB_DFS

**0 / 71 problem placements solved (0.0%)**

🟩 Solved: 0 · 🟨 In Progress: 0 · ⬜ Not Started: 71

This tracker is generated recursively. Update the **Status** field in an individual problem README.

### Problems in this directory

| Problem | LeetCode # | Status |
|---|---:|---|
| [144. Binary Tree Preorder Traversal](AA_BinaryTreePreorderTraversal_LT144/README.md) | #144 | ⬜ Not Started |
| [94. Binary Tree Inorder Traversal](AB_BinaryTreeInorderTraversal_LT94/README.md) | #94 | ⬜ Not Started |
| [145. Binary Tree Postorder Traversal](AC_BinaryTreePostorderTraversal_LT145/README.md) | #145 | ⬜ Not Started |
| [589. Nary Tree Preorder Traversal](AD_NaryTreePreorderTraversal_LT589/README.md) | #589 | ⬜ Not Started |
| [590. Nary Tree Postorder Traversal](AE_NaryTreePostorderTraversal_LT590/README.md) | #590 | ⬜ Not Started |
| [104. Maximum Depthof Binary Tree](AF_MaximumDepthofBinaryTree_LT104/README.md) | #104 | ⬜ Not Started |
| [100. Same Tree](AG_SameTree_LT100/README.md) | #100 | ⬜ Not Started |
| [101. Symmetric Tree](AH_SymmetricTree_LT101/README.md) | #101 | ⬜ Not Started |
| [226. Invert Binary Tree](AI_InvertBinaryTree_LT226/README.md) | #226 | ⬜ Not Started |
| [110. Balanced Binary Tree](AJ_BalancedBinaryTree_LT110/README.md) | #110 | ⬜ Not Started |
| [872. Leaf Similar Trees](AK_LeafSimilarTrees_LT872/README.md) | #872 | ⬜ Not Started |
| [965. Univalued Binary Tree](AL_UnivaluedBinaryTree_LT965/README.md) | #965 | ⬜ Not Started |
| [112. Path Sum](AM_PathSum_LT112/README.md) | #112 | ⬜ Not Started |
| [257. Binary Tree Paths](AN_BinaryTreePaths_LT257/README.md) | #257 | ⬜ Not Started |
| [113. Path Sum II](AO_PathSumII_LT113/README.md) | #113 | ⬜ Not Started |
| [129. Sum Rootto Leaf Numbers](AP_SumRoottoLeafNumbers_LT129/README.md) | #129 | ⬜ Not Started |
| [1022. Sumof Root To Leaf Binary Numbers](AQ_SumofRootToLeafBinaryNumbers_LT1022/README.md) | #1022 | ⬜ Not Started |
| [988. Smallest String Starting From Leaf](AR_SmallestStringStartingFromLeaf_LT988/README.md) | #988 | ⬜ Not Started |
| [1080. Insufficient Nodesin Rootto Leaf Paths](AS_InsufficientNodesinRoottoLeafPaths_LT1080/README.md) | #1080 | ⬜ Not Started |
| [1457. Pseudo Palindromic Pathsina Binary Tree](AT_PseudoPalindromicPathsinaBinaryTree_LT1457/README.md) | #1457 | ⬜ Not Started |
| [437. Path Sum III](AU_PathSumIII_LT437/README.md) | #437 | ⬜ Not Started |
| [1448. Count Good Nodesin Binary Tree](AV_CountGoodNodesinBinaryTree_LT1448/README.md) | #1448 | ⬜ Not Started |
| [1026. Maximum Difference Between Nodeand Ancestor](AW_MaximumDifferenceBetweenNodeandAncestor_LT1026/README.md) | #1026 | ⬜ Not Started |
| [543. Diameterof Binary Tree](AX_DiameterofBinaryTree_LT543/README.md) | #543 | ⬜ Not Started |
| [687. Longest Univalue Path](AY_LongestUnivaluePath_LT687/README.md) | #687 | ⬜ Not Started |
| [124. Binary Tree Maximum Path Sum](AZ_BinaryTreeMaximumPathSum_LT124/README.md) | #124 | ⬜ Not Started |
| [572. Subtreeof Another Tree](BA_SubtreeofAnotherTree_LT572/README.md) | #572 | ⬜ Not Started |
| [951. Flip Equivalent Binary Trees](BB_FlipEquivalentBinaryTrees_LT951/README.md) | #951 | ⬜ Not Started |
| [1367. Linked Listin Binary Tree](BC_LinkedListinBinaryTree_LT1367/README.md) | #1367 | ⬜ Not Started |
| [235. Lowest Common Ancestorofa Binary Search Tree](BD_LowestCommonAncestorofaBinarySearchTree_LT235/README.md) | #235 | ⬜ Not Started |
| [236. Lowest Common Ancestorofa Binary Tree](BE_LowestCommonAncestorofaBinaryTree_LT236/README.md) | #236 | ⬜ Not Started |
| [1123. Lowest Common Ancestorof Deepest Leaves](BF_LowestCommonAncestorofDeepestLeaves_LT1123/README.md) | #1123 | ⬜ Not Started |
| [1483. Kth Ancestorofa Tree Node](BG_KthAncestorofaTreeNode_LT1483/README.md) | #1483 | ⬜ Not Started |
| [700. Searchina Binary Search Tree](BH_SearchinaBinarySearchTree_LT700/README.md) | #700 | ⬜ Not Started |
| [98. Validate Binary Search Tree](BI_ValidateBinarySearchTree_LT98/README.md) | #98 | ⬜ Not Started |
| [108. Convert Sorted Arrayto Binary Search Tree](BJ_ConvertSortedArraytoBinarySearchTree_LT108/README.md) | #108 | ⬜ Not Started |
| [230. Kth Smallest Elementina BST](BK_KthSmallestElementinaBST_LT230/README.md) | #230 | ⬜ Not Started |
| [530. Minimum Absolute Differencein BST](BL_MinimumAbsoluteDifferenceinBST_LT530/README.md) | #530 | ⬜ Not Started |
| [938. Range Sumof BST](BM_RangeSumofBST_LT938/README.md) | #938 | ⬜ Not Started |
| [701. Insertintoa Binary Search Tree](BN_InsertintoaBinarySearchTree_LT701/README.md) | #701 | ⬜ Not Started |
| [450. Delete Nodeina BST](BO_DeleteNodeinaBST_LT450/README.md) | #450 | ⬜ Not Started |
| [669. Trima Binary Search Tree](BP_TrimaBinarySearchTree_LT669/README.md) | #669 | ⬜ Not Started |
| [538. Convert BSTto Greater Tree](BQ_ConvertBSTtoGreaterTree_LT538/README.md) | #538 | ⬜ Not Started |
| [99. Recover Binary Search Tree](BR_RecoverBinarySearchTree_LT99/README.md) | #99 | ⬜ Not Started |
| [105. Construct Binary Treefrom Preorderand Inorder Traversal](BS_ConstructBinaryTreefromPreorderandInorderTraversal_LT105/README.md) | #105 | ⬜ Not Started |
| [106. Construct Binary Treefrom Inorderand Postorder Traversal](BT_ConstructBinaryTreefromInorderandPostorderTraversal_LT106/README.md) | #106 | ⬜ Not Started |
| [1008. Construct Binary Search Treefrom Preorder Traversal](BU_ConstructBinarySearchTreefromPreorderTraversal_LT1008/README.md) | #1008 | ⬜ Not Started |
| [654. Maximum Binary Tree](BV_MaximumBinaryTree_LT654/README.md) | #654 | ⬜ Not Started |
| [889. Construct Binary Treefrom Preorderand Postorder Traversal](BW_ConstructBinaryTreefromPreorderandPostorderTraversal_LT889/README.md) | #889 | ⬜ Not Started |
| [1028. Recovera Tree From Preorder Traversal](BX_RecoveraTreeFromPreorderTraversal_LT1028/README.md) | #1028 | ⬜ Not Started |
| [114. Flatten Binary Treeto Linked List](BY_FlattenBinaryTreetoLinkedList_LT114/README.md) | #114 | ⬜ Not Started |
| [617. Merge Two Binary Trees](BZ_MergeTwoBinaryTrees_LT617/README.md) | #617 | ⬜ Not Started |
| [623. Add One Rowto Tree](CA_AddOneRowtoTree_LT623/README.md) | #623 | ⬜ Not Started |
| [814. Binary Tree Pruning](CB_BinaryTreePruning_LT814/README.md) | #814 | ⬜ Not Started |
| [1325. Delete Leaves Witha Given Value](CC_DeleteLeavesWithaGivenValue_LT1325/README.md) | #1325 | ⬜ Not Started |
| [508. Most Frequent Subtree Sum](CD_MostFrequentSubtreeSum_LT508/README.md) | #508 | ⬜ Not Started |
| [563. Binary Tree Tilt](CE_BinaryTreeTilt_LT563/README.md) | #563 | ⬜ Not Started |
| [337. House Robber III](CF_HouseRobberIII_LT337/README.md) | #337 | ⬜ Not Started |
| [968. Binary Tree Cameras](CG_BinaryTreeCameras_LT968/README.md) | #968 | ⬜ Not Started |
| [1373. Maximum Sum BSTin Binary Tree](CH_MaximumSumBSTinBinaryTree_LT1373/README.md) | #1373 | ⬜ Not Started |
| [979. Distribute Coinsin Binary Tree](CI_DistributeCoinsinBinaryTree_LT979/README.md) | #979 | ⬜ Not Started |
| [1245. Tree Diameter](CJ_TreeDiameter_LT1245/README.md) | #1245 | ⬜ Not Started |
| [1519. Numberof Nodesinthe Sub Tree Withthe Same Label](CK_NumberofNodesintheSubTreeWiththeSameLabel_LT1519/README.md) | #1519 | ⬜ Not Started |
| [1443. Minimum Timeto Collect All Applesina Tree](CL_MinimumTimetoCollectAllApplesinaTree_LT1443/README.md) | #1443 | ⬜ Not Started |
| [2246. Longest Path With Different Adjacent Characters](CM_LongestPathWithDifferentAdjacentCharacters_LT2246/README.md) | #2246 | ⬜ Not Started |
| [834. Sumof Distancesin Tree](CN_SumofDistancesinTree_LT834/README.md) | #834 | ⬜ Not Started |
| [404. Sumof Left Leaves](CO_SumofLeftLeaves_LT404/README.md) | #404 | ⬜ Not Started |
| [366. Find Leavesof Binary Tree](CP_FindLeavesofBinaryTree_LT366/README.md) | #366 | ⬜ Not Started |
| [1302. Deepest Leaves Sum](CQ_DeepestLeavesSum_LT1302/README.md) | #1302 | ⬜ Not Started |
| [865. Smallest Subtreewithallthe Deepest Nodes](CR_SmallestSubtreewithalltheDeepestNodes_LT865/README.md) | #865 | ⬜ Not Started |
| [545. Boundary of Binary Tree](CS_BoundaryOfBinaryTree_LT545/README.md) | #545 | ⬜ Not Started |

<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>
<!-- PROBLEM_TRACKER:END -->
