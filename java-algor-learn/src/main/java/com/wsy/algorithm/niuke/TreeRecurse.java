package com.wsy.algorithm.niuke;


import java.util.ArrayList;
import java.util.List;

public class TreeRecurse {
//    private List<Integer> array = new ArrayList<>();
//    /**
//     *
//     * @param root TreeNode类 the root of binary tree
//     * @return int整型二维数组
//     */
//    public int[][] threeOrders (TreeNode root) {
//        // write code here
//        before(root);
//        middle(root);
//        after(root);
//        int len = array.size()/3;
//        int[][] res = new int[3][len];
//        for(int i=0;i<3;i++){
//            for(int j=0;j<len;j++)
//                res[i][j] = array.get(i*len + j);
//        }
//    }
//    private void before(TreeNode root){
//        if(root!=null){
//            before(root.left);
//            array.add(root.val);
//            before(root.right);
//        }
//    }
//    private void middle(TreeNode root){
//        if(root!=null){
//            array.add(root.val);
//            before(root.left);
//            before(root.right);
//        }
//    }
//    private void after(TreeNode root){
//        if(root!=null){
//            before(root.left);
//            before(root.right);
//            array.add(root.val);
//        }
//    }

}
