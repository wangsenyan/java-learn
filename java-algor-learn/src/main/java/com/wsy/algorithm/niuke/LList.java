package com.wsy.algorithm.niuke;
public class LList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    ListNode build(int[] list){
        if(list.length==0) return null;
        ListNode root = new ListNode(list[0]);
        ListNode cur = root;
        for (int i = 1; i < list.length; i++) {
            ListNode inode = new ListNode(list[i]);
            cur.next = inode;
            cur = inode;
        }
        return root;
    }
}

