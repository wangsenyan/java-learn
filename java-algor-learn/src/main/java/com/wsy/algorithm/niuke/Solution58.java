package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.wsy.algorithm.niuke.BinaryTree.TreeNode;
public class Solution58 {
//      public class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode() {}
//      TreeNode(int val) { this.val = val; }
//      TreeNode(int val, TreeNode left, TreeNode right) {
//          this.val = val;
//          this.left = left;
//          this.right = right;
//      }
//  }
public List<Integer> inorderTraversal(TreeNode root) {
    Integer integer = Integer.valueOf(" 1 ".trim());
    List<Integer> list = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
        if (cur != null) {
            stack.push(cur);
            cur = cur.left;
        } else {
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
    }
    return list;
}

    public static void main(String[] args) {
        Solution58 solution58 = new Solution58();
        TreeNode build = new BinaryTree().build(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        List<Integer> integers = solution58.inorderTraversal(build);
    }
}
