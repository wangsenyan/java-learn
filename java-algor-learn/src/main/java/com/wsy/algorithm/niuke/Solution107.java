package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.wsy.algorithm.niuke.BinaryTree.TreeNode;
public class Solution107 {
    private Map<TreeNode,Integer> map;
    public int rob(TreeNode root) {
        map = new HashMap<>();
        //每颗子树最大值 left + right : root  层序遍历
        return robHelper(root);
    }
    private int robHelper(TreeNode root){
        //偷取节点与不偷取的差异
        if(root==null) return 0;
        if(map.containsKey(root))
            return map.get(root);
        //偷取改节点
        int res1 = root.val;
        if(root.left!=null){
            res1+=robHelper(root.left.left) + robHelper(root.left.right);
        }
        if(root.right!=null){
            res1+=robHelper(root.right.left) + robHelper(root.right.right);
        }
        //不偷取改节点
        int res2= robHelper(root.left) + robHelper(root.right);
        int res = Math.max(res1,res2);
        map.put(root,res);
        return res;
    }

    public static void main(String[] args) {
        Integer[] list = {1,2,3,4,5,6,7,8,9,1};
        TreeNode build = new BinaryTree().build(list);
        int rob = new Solution107().rob(build);
    }
}
