package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution95 {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }
    public ListNode detectCycle(ListNode head) {
        //入度为2的节点
        //判断是否有环???
        //判断是否是入环节点???

        //方法一:set
        Set<ListNode> set = new HashSet<>();
        ListNode res = null;
        while(head!=null){
            if(set.contains(head))
            {
                res = head;
                break;
            }
            set.add(head);
            head = head.next;
        }
        return  res;
    }
    public ListNode detectCycle1(ListNode head) {
        //方法2:快慢指针???
        //相遇的时候,多出的次数是?
        ListNode root = head;
        ListNode res = null;
        if(head!=null){
            ListNode slow = head;
            ListNode fast = head.next;
            while(fast!=null){
                //从这里面返回的是正确的
                if(slow==fast){
                    //si是环长度的倍数
                    int l = 1;//l为环的长度
                    while (fast.next!=fast){
                        fast = fast.next;
                        l++;
                    }
                    slow = root;
                    fast = root;
                    for (int i = 0; i < l; i++) {
                        fast = fast.next;
                    }
                    while (slow!=fast){
                        slow = slow.next;
                        fast = fast.next;
                    }
                    //快的比慢的多跑的是环的数量
                    res = slow;
                    break;
                }else{
                    slow = slow.next;
                    if(fast.next==null) break;
                    fast = fast.next.next;
                }
            }
        }
        return res;
    }
}
