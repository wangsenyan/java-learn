package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class Solution166_0 {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        long a = numerator, b = denominator;
        if (a > 0 && b < 0 || a < 0 && b > 0) sb.append('-');
        a = Math.abs(a);
        b = Math.abs(b);
        sb.append(a / b);
        if (a % b == 0) return sb.toString();
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while ((a = (a % b) * 10) > 0 && !map.containsKey(a)) {
            map.put(a, sb.length());
            sb.append(a / b);
        }
        if (a == 0) return sb.toString();
        return sb.insert(map.get(a).intValue(), '(').append(')').toString();
    }

    public static void main(String[] args) {
        Solution166_0 solution166_0 = new Solution166_0();
        int n = 133;
        int d = 31;
        String s = solution166_0.fractionToDecimal(n, d);
        System.out.println(s);
    }
}
