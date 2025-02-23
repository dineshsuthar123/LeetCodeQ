package Daily_questions;

class TreeNode_889 {
    int val;
    TreeNode_889 left, right;
    TreeNode_889(int x) { val = x; }
}

class Solution1_889 {
    int preIdx = 0, postIdx = 0;

    public TreeNode_889 constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode_889 root = new TreeNode_889(preorder[preIdx++]);

        if (root.val != postorder[postIdx]) {
            root.left = constructFromPrePost(preorder, postorder);
        }
        if (root.val != postorder[postIdx]) {
            root.right = constructFromPrePost(preorder, postorder);
        }
        postIdx++;

        return root;
    }
}

class Solution2_889 {
    public TreeNode_889 constructFromPrePost(int[] preorder, int[] postorder) {
        int[] index = new int[]{0};
        return construct(preorder, postorder, index, 0, preorder.length - 1);
    }

    private TreeNode_889 construct(int[] preorder, int[] postorder, int[] index, int l, int h) {
        if (index[0] >= preorder.length || l > h)
            return null;

        TreeNode_889 root = new TreeNode_889(preorder[index[0]++]);
        if (l == h) return root;

        int i;
        for (i = l; i <= h; i++) {
            if (postorder[i] == preorder[index[0]]) break;
        }
        if (l <= h) {
            root.left = construct(preorder, postorder, index, l, i);
            root.right = construct(preorder, postorder, index, i + 1, h - 1);
        }
        return root;
    }
}

public class _889 {
    // Helper function to print the tree in order (for verification)
    public static void printInOrder(TreeNode_889 root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};

        Solution1_889 sol1 = new Solution1_889();
        TreeNode_889 root1 = sol1.constructFromPrePost(preorder, postorder);
        System.out.print("Solution1 Inorder: ");
        printInOrder(root1);
        System.out.println();

        Solution2_889 sol2 = new Solution2_889();
        TreeNode_889 root2 = sol2.constructFromPrePost(preorder, postorder);
        System.out.print("Solution2 Inorder: ");
        printInOrder(root2);
        System.out.println();
    }
}
