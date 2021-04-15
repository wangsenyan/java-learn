package com.wsy.algorithm.niuke;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution55 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode cur = head;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]!= null){
                priorityQueue.add(lists[i]);
                //lists[i] = lists[i].next;
            }
        }
        while (!priorityQueue.isEmpty()){
            ListNode peek = priorityQueue.poll();
            ListNode node = new ListNode(peek.val);
            cur.next = node;
            cur = cur.next;
            if(peek.next!=null){
                priorityQueue.offer(peek.next);
            }
        }
        return head.next;
    }
}
