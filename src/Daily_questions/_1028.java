package Daily_questions;

class TreeNode_1028 {
    int val;
    TreeNode_1028 left, right;
    TreeNode_1028(int val) { this.val = val; }
}

class Solution_1028 {
    private String s;
    private int idx, level;

    public TreeNode_1028 recoverFromPreorder(String traversal) {
        this.s = traversal;
        this.idx = 0;
        this.level = 0;
        TreeNode_1028 node = new TreeNode_1028(-1);
        this.helper(node, 0);
        return node.left;
    }

    private void helper(TreeNode_1028 parent, int lvl) {
        while (this.idx < this.s.length() && lvl == level) {
            int num = 0;
            while (this.idx < this.s.length() && Character.isDigit(this.s.charAt(this.idx))) {
                num = num * 10 + (this.s.charAt(this.idx++) - '0');
            }
            TreeNode_1028 node = new TreeNode_1028(num);
            if (parent.left == null)
                parent.left = node;
            else
                parent.right = node;

            this.level = 0;
            while (this.idx < this.s.length() && this.s.charAt(this.idx) == '-') {
                this.level++;
                this.idx++;
            }
            this.helper(node, lvl + 1);
        }
    }
}

public class _1028 {
    public static void main(String[] args) {
        Solution_1028 solution = new Solution_1028();
        TreeNode_1028 root = solution.recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(root.val); // Example usage
    }
}
