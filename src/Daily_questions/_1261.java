package Daily_questions;

import java.util.*;

class TreeNode {
    int val;
    TreeNode_1028 left, right;
    TreeNode(int val) { this.val = val; }
}

class FindElements {
    BitSet recoveredValues;

    public FindElements(TreeNode_1028 root) {
        root.val = 0;
        recoveredValues = new BitSet();
        recoverTree(root);
    }

    private void recoverTree(TreeNode_1028 root) {
        if (root == null) return;
        recoveredValues.set(root.val);
        if (root.left != null) {
            root.left.val = 2 * root.val + 1;
            recoverTree(root.left);
        }
        if (root.right != null) {
            root.right.val = 2 * root.val + 2;
            recoverTree(root.right);
        }
    }

    public boolean find(int target) {
        return recoveredValues.get(target);
    }
}

public class _1261 {
    public static void main(String[] args) {
        TreeNode_1028 root = new TreeNode_1028(-1);
        root.left = new TreeNode_1028(-1);
        root.right = new TreeNode_1028(-1);
        root.left.left = new TreeNode_1028(-1);

        FindElements findElements = new FindElements(root);
        System.out.println(findElements.find(1)); // Example usage
        System.out.println(findElements.find(3));
    }
}
