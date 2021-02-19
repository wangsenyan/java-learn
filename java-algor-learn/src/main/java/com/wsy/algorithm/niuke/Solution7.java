package com.wsy.algorithm.niuke;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedMap;

public class Solution7 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        //关键k个中找到最小的数
        int len = lists.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return lists[o1].val - lists[o2].val;
            }
        });
        for (int i = 0; i < len; i++) {
            if(lists[i]!=null)
              queue.add(i);
        }
        Integer peek = -1;
        ListNode head = null;
        ListNode curr = null;
        while (!queue.isEmpty()){
            peek = queue.remove();
            if(head==null){
                head = curr = lists[peek];
            }else{
                curr.next = lists[peek];
            }
            if(lists[peek].next!=null)
            {
                lists[peek] = lists[peek].next;
                queue.add(peek);
            }
        }
        return head;
    }
}
