package com.wsy.algorithm.niuke;

public class Solution22 {
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //加法,递归
        return addTwoNumbersSub(l1,l2,0);

    }
    private ListNode addTwoNumbersSub(ListNode l1,ListNode l2,int rmd ){
        System.out.println(rmd);
        int s = 0;
        if(l1!=null && l2!=null){
            s = l1.val + l2.val + rmd;
            l1.val = s%10;
            l1.next = addTwoNumbersSub(l1.next,l2.next,s/10);
            return l1;
        }else if(l1!=null){
            s = l1.val + rmd;
            l1.val = s%10;
            l1.next = addTwoNumbersSub(l1.next,null,s/10);
            return l1;
        }else if(l2!=null){
            s = l2.val + rmd;
            l2.val = s%10;
            l2.next = addTwoNumbersSub(null,l2.next,s/10);
            return l2;
        }else if(rmd>0){
            return new ListNode(rmd);
        }else{
            return null;
        }
    }
}
