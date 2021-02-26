package com.wsy.algorithm.niuke;


import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int x) { val = x; }
    }
    public TreeNode build(Integer[] list){
        if(list == null || list[0] == null) return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(list[0]);
        queue.add(root);
        int i=1,len=list.length;
        while (!queue.isEmpty()&&i<len) {
            TreeNode first = queue.removeFirst();
            if(i<len && list[i]!=null){
                TreeNode l = new TreeNode(list[i]);
                first.left = l;
                queue.add(l);
            }
            if(i+1<len && list[i+1]!=null){
                TreeNode r = new TreeNode(list[i+1]);
                first.right = r;
                queue.add(r);
            }
            i+=2;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] list={2,1,null};
        TreeNode r = new BinaryTree().build(list);
    }

}
