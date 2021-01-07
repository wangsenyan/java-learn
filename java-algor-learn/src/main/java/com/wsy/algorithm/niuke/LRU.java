package com.wsy.algorithm.niuke;

import java.util.*;

/**
 * 此方法耗时太多
 */
public class LRU {
    class LRUChace extends LinkedHashMap<Integer,Integer>{
        private int capacity;
        public LRUChace(int capacity){
            super(capacity,0.75F,true);
            this.capacity = capacity;
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return super.size()>capacity;
        }
    }
    public int[] LRU (int[][] operators, int k){
        int rowSize = operators.length;
        int res[] = new int[rowSize];
        LRUChace lruChace = new LRUChace(k);
        for(int i=0,j=0;i<rowSize;i++){
            int opt = operators[i][0];
            if(opt == 1)
                lruChace.put(operators[i][1],operators[i][2]);
            else {
                Integer r = lruChace.get(operators[i][1]);
                res[j++] = ( r.equals(null) ? -1: r) ;
            }
        }
        return  res;
    }

    public static void main(String[] args) {
        int[][] p = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        Solution solution = new Solution();
        int[] ans = solution.LRU(p, 3);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}