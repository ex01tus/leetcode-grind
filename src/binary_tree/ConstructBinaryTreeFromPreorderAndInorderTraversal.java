package binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * Difficulty: Medium
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private Map<Integer, Integer> inorderValueToIndex;
    private int preorderRootIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderValueToIndex = toMap(inorder);
        preorderRootIndex = 0;
        return toTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode toTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        TreeNode root = new TreeNode();
        root.val = preorder[preorderRootIndex++];
        int inorderRootIndex = inorderValueToIndex.get(root.val);

        // left before right!
        root.left = toTree(preorder, left, inorderRootIndex - 1);
        root.right = toTree(preorder, inorderRootIndex + 1, right);

        return root;
    }

    private Map<Integer, Integer> toMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return map;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
