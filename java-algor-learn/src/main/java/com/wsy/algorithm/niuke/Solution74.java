package com.wsy.algorithm.niuke;
import com.wsy.algorithm.niuke.BinaryTree.TreeNode;
public class Solution74 {
    private int max;
    public int maxPathSum(TreeNode root) {
        //ret --> [root.val,left.val + root.val,right.val + root.val]
        //max --> [max,left_ret,right_ret,left_ret + root.val,right_ret + root.val,left_ret + root.val + right_ret]
        max = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return max;
    }
    private int maxPathSumHelper(TreeNode root){
       if(root==null) return 0;
       int left = Math.max(maxPathSumHelper(root.left),0);//子树为负,抛弃
       int right = Math.max(maxPathSumHelper(root.right),0);
       max = Math.max(max,left + right + root.val);
       return Math.max(left,right) + root.val;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,2,3};
        TreeNode build = new BinaryTree().build(nums);
        int i = new Solution74().maxPathSum(build);
    }
}
