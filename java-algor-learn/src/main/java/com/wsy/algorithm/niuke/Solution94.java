package com.wsy.algorithm.niuke;

class Solution94 {
    public int orchestraLayout(int num, int xPos, int yPos) {
        int l = 0;
        int r = num - 1;
        int u = 0;
        int d = num - 1;
        int t = 0;
        while (l <= r && u <= d){
            for (int i = l; i <= r; i++) {
                if(u==xPos && yPos==i)
                    return t % 9 +1;
                t++;
            }
            u++;
            for (int i = u; i <= d; i++) {
                if(i==xPos && r==yPos)
                    return t % 9 + 1;
                t++;
            }
            r--;
            for (int i = r; i >= l && u <= d; i--) {
                if(d==xPos && i==yPos)
                    return t % 9 +1;
                t++;
            }
            d--;
            for (int i = d; i >= u && l <= r; i--) {
                if(i==xPos && l==yPos)
                    return t % 9 +1;
                t++;
            }
            l++;
        }
        return t % 9 + 1;
    }
    public int orchestraLayout1(int num, int xPos, int yPos) {
        int t = 1;
        //找到第几圈
        int x = Math.min(xPos +1,num - xPos);
        int y = Math.min(yPos +1,num - yPos);
        int c = Math.min(x,y);//第几圈
        for (int i = 1; i < c; i++) {
            t+=(num - i) * 4;
        }
        int l = c;
        int r = num - c;
        int u = c;
        int d = num - c;
        for (int i = l; i <= r; i++) {
            if(u==xPos && yPos==i)
                return t % 9 +1;
            t++;
        }
        u++;
        for (int i = u; i <= d; i++) {
            if(i==xPos && r==yPos)
                return t % 9 + 1;
            t++;
        }
        r--;
        for (int i = r; i >= l && u <= d; i--) {
            if(d==xPos && i==yPos)
                return t % 9 +1;
            t++;
        }
        d--;
        for (int i = d; i >= u && l <= r; i--) {
            if(i==xPos && l==yPos)
                return t % 9 +1;
            t++;
        }
        l++;
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(new Solution94().orchestraLayout1(4, 0, 0));

    }
}