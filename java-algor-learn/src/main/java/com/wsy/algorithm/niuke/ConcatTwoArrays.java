package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;

public class ConcatTwoArrays {
    public int[] concatTwoArrays(int[]arr1,int[]arr2){
        int len1=arr1.length;
        int len2=arr2.length;
        ArrayList<Integer> list = new ArrayList<>();
        int i=0,j=0,last = Integer.MAX_VALUE;
        while (i<len1 || j<len2){
            int a1 = i<len1?arr1[i]:Integer.MAX_VALUE;
            int a2 = j<len2?arr2[j]:Integer.MAX_VALUE;
            if(a1<=a2) {
                if(last!=a1) {
                    list.add(a1);
                    last=a1;
                }
                i++;
            }else {
                if(last!=a2) {
                    list.add(a2);
                    last = a2;
                }
                j++;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    public int[] concatTwoArrays1(int[]arr1,int[]arr2){
        int p1=0,p2=0,index=-1;
        ArrayList<Integer> list = new ArrayList<>();
        while (p1<arr1.length && p2< arr2.length){
            int tmp = arr1[p1] < arr2[p2] ? arr1[p1++] : arr2[p2++];
            if(list.isEmpty() || list.get(index)<tmp) {
                list.add(tmp);
                index++;
            }
        }
        while (p1<arr1.length){
            int tmp = arr1[p1++];
            if(list.isEmpty() || list.get(index)<tmp) {
                list.add(tmp);
                index++;
            }
        }
        while (p2<arr2.length){
            int tmp = arr2[p2++];
            if(list.isEmpty() || list.get(index)<tmp) {
                list.add(tmp);
                index++;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        int[] a = {1,3,6};
        int[] b = {2,3,7,Integer.MAX_VALUE};
        int[] ints = new ConcatTwoArrays().concatTwoArrays1(a, b);

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
