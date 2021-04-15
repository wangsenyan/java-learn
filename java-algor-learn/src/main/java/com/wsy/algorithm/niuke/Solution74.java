package com.wsy.algorithm.niuke;
import com.wsy.algorithm.niuke.BinaryTree.TreeNode;
public class Solution74 {
    private int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE/2;
        helper(root);
        return max;
    }
    private int helper(TreeNode root){
        int left = -1001;
        int right = -1001;
        if(root.left!=null)
            left = helper(root.left);
        if(root.right!=null)
            right = helper(root.right);
        System.out.println(left + " " + right + " "+ root.val);
        max = Math.max( Math.max(root.val,left + right + root.val),max);

        if(left > right){
            max = Math.max(max,Math.max(left,left+root.val));
            if(root.val + left > root.val )
                return left + root.val;
            else
                return root.val;
        }else{
            max = Math.max(Math.max(right,right + root.val),max);
            if(root.val + right > root.val)
                return right + root.val;
            else
                return root.val;
        }
    }

    public static void main(String[] args) {
        Integer[] list = new Integer[]{1,-2,-3,1,3,-2,null,-1};
        TreeNode build = new BinaryTree().build(list);
        int i = new Solution74().maxPathSum(build);
    }
}
