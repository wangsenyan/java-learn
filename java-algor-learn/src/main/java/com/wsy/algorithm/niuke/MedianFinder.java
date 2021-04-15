package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    //最大堆和最小堆
    PriorityQueue<Integer> maxQueue;// 前n/2大的最大堆
    PriorityQueue<Integer> minQueue;// 后n-n/2的最小堆
    private int total;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        minQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1- o2;
            }
        });
        total = 0;
    }

    public void addNum(int num) {
        //如何加入,首先加入
        //和队首比较
        maxQueue.offer(num);
        total++;
        Integer maxMin = minQueue.peek();
        Integer minMax = maxQueue.peek();
        if(maxQueue.size() > total - total /2){
            minQueue.offer(maxQueue.poll());
        }else{
            if(maxMin!=null && maxMin < minMax){
                minQueue.offer(maxQueue.poll());
                maxQueue.offer(minQueue.poll());
            }
        }
    }

    public double findMedian() {
        if(total%2==0) return (maxQueue.peek() + minQueue.peek())/2.0;
        return maxQueue.peek();
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(4);
        finder.addNum(9);
        finder.addNum(11);
        finder.addNum(6);
        double median = finder.findMedian();
    }

}
