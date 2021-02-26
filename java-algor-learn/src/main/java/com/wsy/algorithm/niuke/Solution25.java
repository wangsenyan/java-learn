package com.wsy.algorithm.niuke;
import com.wsy.algorithm.niuke.LList.ListNode;
public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        //从前到后,如果add==k,返回翻转,否则返回
        if(k<2 || head == null) return head;
        int i=0;
        ListNode first = head,last=null;
        while (head!=null && i<k){
            if(i==k-1) last = head;
            head = head.next;
            i++;
        }
        if(i<k) return  first;
        last.next = null;
        ListNode next_first = reverseKGroup(head, k);
        ListNode curr_first = reverse(first);
        first.next = next_first;
        return curr_first;
    }
    private ListNode reverse(ListNode root){
        ListNode pre=null,next,head=root;
        while (head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int [] lt = {1,2,3,4,5};
        ListNode root = new LList().build(lt);
        ListNode reverse = new Solution25().reverseKGroup(root,0);
    }
}
