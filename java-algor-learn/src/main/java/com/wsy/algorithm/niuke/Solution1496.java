package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.Set;

public class Solution1496 {
    public boolean isPathCrossing(String path) {
        //N 向上加1向下减1 E向左加1向右减1
        int N = 0,E = 0;
        int K = 10000;
        Set<Integer> seen = new HashSet();
        seen.add(0);

        for(char c : path.toCharArray()){
            switch(c){
                case 'N':
                    N++;break;
                case 'S':
                    N--;break;
                case 'E':
                    E++;break;
                case 'W':
                    E--;break;
            }
            int mul = N + K * E;
            if(seen.contains(mul))
                return true;
            seen.add(mul);
        }
        return false;
    }
}
