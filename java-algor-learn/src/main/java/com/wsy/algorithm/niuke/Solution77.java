package com.wsy.algorithm.niuke;

public class Solution77 {
    private int[] nums;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        return binFind(0,n-1);
    }
    public int binFind(int left,int right){
        if(left == right) return 0;
        int mid = (left + right)/2;
        int lnum = binFind(left,mid);
        int rnum = binFind(mid+1,right);
        //比较,然后排序 [left,mid] [mid+1,right] 都是递增的
        //nums[i] > 2 * nums[j]
        int ret = lnum + rnum;
        int i = left;
        int j = mid + 1;
        while(i<=mid){
            while (j<=right && (long)nums[i] > 2 * (long) nums[j]){
                j++;
            }
            ret+= j - mid - 1;
            i++;
        }
        //合并
        int[] sorted = new int[right - left + 1];
        int idx = 0;
        int p1 = left, p2 = mid + 1;
        while(p1<=mid || p2 <=right){
            if(p1<=mid && p2<=right){
                if(nums[p1]<nums[p2]) sorted[idx++]=nums[p1++];
                else sorted[idx++]=nums[p2++];
            }else{
                sorted[idx++] = p1<=mid?nums[p1++]:nums[p2++];
            }
        }
        for (int k = left; k <=right; k++) {
            nums[k] = sorted[k-left];
        }
        return ret;
    }
    private int binSearch(int[] nums,int num){
        int n = nums.length;
        int l = 0, r = n -1;
        while (l <= r){
            int mid = l + ((r - l)>>1);
            if(nums[mid]>num)
                r = mid - 1;
            else if(nums[mid]<num)
                l = mid + 1;
            else
                return mid;
        }
        return r;
    }

    public static void main(String[] args) {
        int[] ints = {};
        Solution77 solution77 = new Solution77();

        int i = solution77.reversePairs(ints);
    }
}
