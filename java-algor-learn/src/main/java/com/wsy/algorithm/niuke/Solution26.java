package com.wsy.algorithm.niuke;

import java.util.*;
import java.util.stream.Collectors;

public class Solution26 {
    public String findInLine(int[] arr,int len){
        Arrays.sort(arr);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<len;i++){
            map.put(arr[i],i);
        }
        StringBuilder s = new StringBuilder();
        for(int i=len-1;i>=0;i--){
            int a = arr[i];
            for(int j=0;j<i;j++){
                int b = arr[j];
                if((a-b)%2==0){
                    Integer index = map.get((a - b) / 2);
                    if(index!=null && index!=i && index!=j)
                       return s.append(a).append(" ").append(b).append(" ").append((a-b)/2).toString();
                }
            }
        }
        return String.valueOf(0);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.valueOf(scanner.nextLine());
        String[] s = scanner.nextLine().split(" ");
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = Integer.valueOf(s[i]);
        }
        System.out.println(new Solution26().findInLine(list, n));
    }
}
