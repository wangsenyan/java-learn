package com.wsy.algorithm.niuke;

public class Solution223 {
    /**
     int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
     int area1 = (C - A) * (D - B), area2 = (G - E) * (H - F);
     if (C <= E || F >= D || B >= H || A >= G) {
     return area1 + area2; //无重叠
     }
     int topX = std::min(G, C), topY = std::min(H, D);
     int bottomX = std::max(E, A), bottomY = std::max(B, F);
     return area1 - (topX - bottomX) * (topY - bottomY) + area2;
     }
     */
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B), area2 = (G - E) * (H - F);
        if (C <= E || F >= D || B >= H || A >= G) {
            return area1 + area2; //无重叠
        }
        int topX = Math.min(G, C), topY = Math.min(H, D);
        int bottomX = Math.max(E, A), bottomY = Math.max(B, F);
        return area1 - (topX - bottomX) * (topY - bottomY) + area2;
    }
}
