package com.wsy.algorithm.niuke;

public class retainAll {
    public static void main(String[] args) {
        char a[] = {'a','b','c','d'};
        char b[] = {'a','b','d','c','d'};

        System.out.println(d(a,b));

    }
    public static int[] retainAll(int[] array1,int[]array2){
        int len1 = array1.length;
        int len2 = array2.length;
        int res[] = new int[Math.min(len1,len2)];
        int k = 0;
        int j=0;
        for (int i = 0; i < len1; i++) {
           while (j<len2){
               if(array1[i]> array2[j])
                 j++;
               else if(array1[j]==array2[i]) {
                   res[k++] = array1[i];
                   j++;
               }
               else
                   break;
           }
        }
        int r[] = new int[k];
        for (int i = 0; i < k; i++) {
            r[i]=res[i];
        }
        return r;
    }
    public static char d(char[] chars1, char[] chars2){
        int len1=chars1.length;
        int len2=chars2.length;
        char b=0;
        int i=0;
        for(;i<Math.min(len1,len2);i++){
            int r = chars1[i] ^ chars2[i];
            if(r!=0){
                break;
            }
        }
        return len1>len2?chars1[i]:chars2[i];
    }
}
