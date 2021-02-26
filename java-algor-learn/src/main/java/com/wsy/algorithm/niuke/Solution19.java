package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class Solution19 {
    private HashMap<Integer,Integer> map = new HashMap<>();
    public int numSquares(int n) {
        int depth = 0;
        if(n == 0) return 0;
        int s = (int)Math.sqrt(n);
        for(int i=1;i<=s;i++){
            int nn = n-i*i;
            Integer v = map.get(nn);
            int dep = 0;
            if(v!=null) dep =v;
            else{
                dep= 1 + numSquares(nn);
                map.put(nn,dep);
            }
            if(depth==0) depth = dep;
            else
                depth = depth > dep ? dep : depth;
        }
        return depth;
    }

    public static void main(String[] args) {
        System.out.println(new Solution19().numSquares(13));
    }
}
