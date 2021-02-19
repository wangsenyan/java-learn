package com.wsy.algorithm.niuke;

public class Solution14 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();//哨兵
        pre.next = head;
        ListNode one = pre;
        ListNode two = pre;
        int l = 0;
        while (l<n && two!=null && two.next!=null){
            one = one.next;
            two = two.next.next;
            l++;
        }
        if(l<n){
            //说明在前面
            if(two==null) l = l * 2 -1;
            else l = l * 2;
            one = pre;
            for (int i = 0; i < l - n; i++) {
                one = one.next;
            }
        }else{
            while (two!=null){
                one = one.next;
                two = two.next;
            }
        }
        one.next = one.next.next;
        return pre.next;
    }
    private int cur = 0;
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if(head==null) return null;
        head.next = removeNthFromEnd1(head.next,n);
        cur++;
        if(n==cur) return head.next;
        return head;
    }
}
