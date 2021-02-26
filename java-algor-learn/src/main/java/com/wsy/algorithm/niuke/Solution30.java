package com.wsy.algorithm.niuke;

public class Solution30 {
    private int[] postorder;
    public boolean verifyPostorder(int[] postorder) {
        this.postorder  = postorder;
        return dfs(0,postorder.length-1);
    }
    private boolean dfs(int start,int end){
        if(start>=end) return true;
        int i=end-1,root = postorder[end];
        while(i>=start){
            if(postorder[i]<root)
                break;
            i--;
        }
        int j=i;
        while(i>=start){
            if(postorder[i]>root) return false;
            i--;
        }
        return dfs(start,j) && dfs(j+1,end-1);
    }

    public static void main(String[] args) {

        int[] arr = {179, 437, 1405, 5227, 8060, 8764, 8248, 4687, 3297, 13038, 12691, 15744, 16195, 15642, 19813, 17128, 21051, 20707, 22177, 21944, 23644, 23281, 19970, 23652, 26471, 31467, 33810, 32300, 33880, 27334, 25987, 35643, 35103, 36489, 42534, 42990, 42942, 37090, 36075, 34516, 16624, 11335, 10737, 44641, 45754, 47096, 46021, 49150, 48013, 49814, 51545, 52555, 50701, 47875, 56783, 57558, 53812, 62008, 61737, 63052, 63478, 62799, 59246, 64765, 64066, 63862, 65384, 67449, 66552, 57741, 45618, 44412, 667, 69718, 75519, 76819, 72971, 79319, 78145, 80615, 84280, 80984, 86598, 85903, 84334, 80867, 87993, 92361, 88465, 87738, 80364, 94380, 94446, 96785, 93694, 76847, 99655, 98675, 97001, 72112};
        boolean b = new Solution30().verifyPostorder(arr);
    }
}
