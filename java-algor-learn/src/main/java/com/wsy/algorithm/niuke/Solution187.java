package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution187 {
    public List<String> findRepeatedDnaSequences0(String s) {
        int n = s.length();
        int l = 10;
        //单次个数是固定的
        Set<String> res = new HashSet<>(),set = new HashSet<>();
        for (int i = 0; i < n - l + 1; i++) {
            String sub = s.substring(i,i+l);
            if(set.contains(sub)) res.add(sub);
            set.add(sub);
        }
        return new LinkedList<>(res);
    }
    public List<String> findRepeatedDnaSequences1(String s) {
        int n = s.length();
        int l = 10;
        if(n < l) return new ArrayList<>();
        //set.contains(key)对key进行hashcode(),要对它遍历
        //相当于手动hashcode 'A'，'C'，'G' 和 'T'
        Map<Character,Integer> code = new HashMap(){{put('A',0);put('C',1);put('G',2);put('T',3);}};
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = code.get(s.charAt(i));
        }
        Set<String> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        int ds = 0;
        for (int i = 0; i < n - l + 1; i++) {
            if(i==0){
                for (int j = 0; j < l; j++) {
                    //编码第一个
                    ds <<= 2;
                    ds |= num[j];
                }
            }else{
                ds <<= 2;
                ds |= num[i + l - 1];
                ds &= ~(3 << 2 * l);
            }
            if(set.contains(ds))
                res.add(s.substring(i,i+10));
            set.add(ds);;
        }
        //为什么LinkedList更费时
        return new ArrayList<>(res);
    }
    /**
     * 关键是将字符和数字进行映射,快速查找,26个字母如何呢???长度可能是无限长的
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        if (n <= L) return new ArrayList();

        // rolling hash parameters: base a
        int a = 4, aL = (int)Math.pow(a, L);

        // convert string to array of integers
        Map<Character, Integer> toInt = new
                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));

        int bitmask = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            // compute bitmask of the current sequence in O(1) time
            if (start != 0) {
                // left shift to free the last 2 bit
                bitmask <<= 2;
                // add a new 2-bits number in the last two bits
                bitmask |= nums[start + L - 1];
                // unset first two bits: 2L-bit and (2L + 1)-bit
                bitmask &= ~(3 << 2 * L);
            }
            // compute hash of the first sequence in O(L) time
            else {
                for(int i = 0; i < L; ++i) {
                    bitmask <<= 2;
                    bitmask |= nums[i];
                }
            }
            // update output and hashset of seen sequences
            if (seen.contains(bitmask)) output.add(s.substring(start, start + L));
            seen.add(bitmask);
        }
        return new ArrayList<String>(output);
    }
    public static void main(String[] args) {

        String s = "AAAAAAAAAAAAA";
        List<String> repeatedDnaSequences = new Solution187().findRepeatedDnaSequences1(s);
        System.out.println(repeatedDnaSequences);
    }
}
