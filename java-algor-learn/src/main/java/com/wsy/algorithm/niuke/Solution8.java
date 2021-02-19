package com.wsy.algorithm.niuke;

public class Solution8 {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
    private double max = - Double.MAX_VALUE;
    private boolean tag = false;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(isValidBST(root.left)){
            if(root.val > max){
                max = root.val;
                return isValidBST(root.right);
            }

        }
        return false;
    }
}
