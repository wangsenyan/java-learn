package com.wsy.algorithm.niuke;
import com.wsy.algorithm.niuke.BinaryTree.TreeNode;
public class Solution75 {
        TreeNode res = null;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root==null) return null;
            if(root == p || root == q) return root;//如果存在,就返回,不存在,递归
            TreeNode left = lowestCommonAncestor(root.left,p,q);
            TreeNode right = lowestCommonAncestor(root.right,p,q);
            //如果left不为空,right不为空,否则返回res
            if(left!=null && right!=null ) return root;
            return left==null ? right : left;
        }
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,2,3};
        TreeNode build = new BinaryTree().build(nums);
    }
}
