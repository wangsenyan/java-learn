package com.wsy.algorithm.niuke;

import java.util.LinkedList;

public class Solution13 {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
          //通过广度遍历吧
          StringBuilder sb = new StringBuilder();
          LinkedList<TreeNode> queue = new LinkedList();
          queue.addLast(root);
          while (!queue.isEmpty()){
              TreeNode r = queue.removeFirst();
              if(r!=null){
                  sb.append(r.val);
                  queue.addLast(r.left);
                  queue.addLast(r.right);
              }else{
                  sb.append("n");
              }
              sb.append(" ");
          }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(" ");
        if(s[0].equals("n")) return null;
        LinkedList<TreeNode> queue = new LinkedList();
        TreeNode root = new TreeNode(Integer.valueOf(s[0]));
        queue.addLast(root);
        int i = 1;
        while (!queue.isEmpty()){
            TreeNode r = queue.removeFirst();
            if(!s[i].equals("n")){
                TreeNode left = new TreeNode(Integer.valueOf(s[i]));
                r.left = left;
                queue.addLast(left);
            }
            if(!s[i+1].equals("n")){
                TreeNode right = new TreeNode(Integer.valueOf(s[i+1]));
                r.right = right;
                queue.addLast(right);
            }
            i+=2;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode deserialize = new Solution13().deserialize("n");
    }
}
