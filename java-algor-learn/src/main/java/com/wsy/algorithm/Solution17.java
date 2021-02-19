package com.wsy.algorithm;

import java.util.LinkedList;

class TreeNode {
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
public class Solution17 {
    private int total = 0;
    private int number;
    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        this.number = sum;
        pathFind(root,number);
        return total;
    }
    //递归执行,每一层都计算包含本次和不包含本次,利用广度搜索
    private void pathFind(TreeNode root,int sum){
        if(sum == 0) total++ ;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i=0;i<len;i++){
                TreeNode first = queue.removeFirst();
                if(first.left!=null){
                    pathSum(first.left,sum - root.val);
                    pathSum(first.left,number);
                    queue.add(first.left);
                }
                if(first.right!=null){
                    pathSum(first.right,sum - root.val);
                    pathSum(first.right,number);
                    queue.add(first.right);
                }
            }
        }
    }
}
