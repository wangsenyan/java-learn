package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution1 {
    public int[] fairCandySwap(int[] A, int[] B) {
        //求和,得出差除2
        //遍历A,如果交换差值为k,B肯定也是
        int sub = 0,i=0,j=0;
        while(i<A.length || j<B.length){
            sub +=(i< A.length? A[i++]:0)  - (j<B.length?B[j++]:0);
        }
        Set<Integer> set = IntStream.of(B).boxed().collect(Collectors.toSet());
        int bi = sub / 2;
        //bi<0 A 需要bi
        //bi>0 B 需要bi
        for(int k=0;k<A.length;k++){
            int numa = A[k] - bi;
            if(numa > 0){
                if(set.contains(numa))
                    return new int[]{A[k],numa};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = {1,2,5};
        int[] b = {2,4};
        int[] ints = new Solution1().fairCandySwap(a, b);
        for (int i = 0; i < 2; i++) {
            System.out.println(ints[i]);
        }

    }
}
