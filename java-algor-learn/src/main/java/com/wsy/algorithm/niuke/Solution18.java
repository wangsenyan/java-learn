package com.wsy.algorithm.niuke;

import java.util.LinkedList;

public class Solution18 {
     public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }
    public String serialize(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        String s = "";
        while(!queue.isEmpty()){
            TreeNode first = queue.removeFirst();
            if(first!=null){
                queue.add(first.left);
                queue.add(first.right);
                s+=first.val;
            }else{
                s+="n";
            }
            s+=" ";
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
         int n = 0;
        int sqrt = (int)Math.sqrt(n);
        String[] sList =  data.split(" ");
        System.out.println(sList.length);
        if(!sList[0].equals("n")) return null;

        TreeNode root = new TreeNode(Integer.valueOf(sList[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        int i= 1;
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode c = queue.removeFirst();
            if(!sList[i].equals("n")){
                System.out.println(sList[i]);
                TreeNode left = new TreeNode(Integer.valueOf(sList[i]));
                c.left = left;
                queue.add(left);
            }
            if(!sList[i+1].equals("n")){
                TreeNode right = new TreeNode(Integer.valueOf(sList[i+1]));
                c.right = right;
                queue.add(right);
            }
            i+=2;
        }
        return root;
    }
}
