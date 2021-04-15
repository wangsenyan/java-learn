package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution70 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode sortList(ListNode head) {
        ListNode root = head;
        while(root!=null && root.next!=null){
            if(root.val > root.next.val){
                int temp = root.val;
                root.val = root.next.val;
                root.next.val = temp;
            }
            root = root.next;
        }
        return head;
    }
}
