package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

/**
 * 十字链表 + hashmap
 */
class AllOne {
//    private Map<String,AllOneNode> map;//链表的头,尾部
//    private AllOneNode head;
//    private AllOneNode tail;
//    /** Initialize your data structure here. */
//    public AllOne() {
//        map = new HashMap<>();
//        head = new AllOneNode();
//        tail = new AllOneNode();
//        head.next = tail;
//        tail.pre = head;
//    }
//
//    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
//    public void inc(String key) {
//        AllOneNode node = map.get(key);
//        if(node!=null){
//            //增加一下
//            node.val++;
//            //先处理上下关系
//            //1. 是否有node.val++值?
//            //2. 是否是lead,影响前后关系
//            //3. 是否只有一个
//            if(node.lead.next==tail || node.lead.next.val!=node.val){
//                //需要插入节点
//                if(node.lead!=node){
//                    //不是领头
//                    node.parent.child = node.child;
//                    node.child.parent = node.parent;
//                }else{
//                    if(node.child==null){
//                        //只有一个节点
//                        node.pre = node.pre.pre;
//                    }
//                }
//            }else{
//                if(node.lead!=node){
//
//                }else{
//                    if(node.child==null){
//
//                    }
//                }
//            }
//            if(node.lead!=node){//不是领队
//
//            }
//            //后处理前后关系
//            if(node.lead.next.val!= node.val){//插入进去
//                //node是领导节点
//                //node不是领导节点
//                node.next = node.lead.next;
//                node.lead.next = node;
//                node.lead = node;
//                if(node.parent!=null)
//                    node.parent.child = node.child;
//                if(node.lead==node) {
//                    node.pre.next = node.child;
//                    node.child.parent = null;
//                }else{
//                    node.parent = node.parent.parent;
//                    node.parent.parent.child = node;
//                }
//                node.child = null;
//            }else{
//                //插入下一个的头节点
//
//            }
//        }else{
//            AllOneNode node1 = new AllOneNode(1);
//            node1.lead = node1;
//            node1.next = head.next;
//            node1.pre = head;
//            head.next.pre = node1;
//            head.next = node1;
//            map.put(key,head.next);
//        }
//    }
//
//    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
//    public void dec(String key) {
//
//    }
//
//    /** Returns one of the keys with maximal value. */
//    public String getMaxKey() {
//
//    }
//
//    /** Returns one of the keys with Minimal value. */
//    public String getMinKey() {
//
//    }
//    class AllOneNode {
//        AllOneNode lead;//领头
//        AllOneNode next;//下一个
//        AllOneNode child;//子节点
//        AllOneNode parent;//父节点
//        AllOneNode pre;//前驱节点
//        int val;
//        public AllOneNode(AllOneNode lead,
//                          AllOneNode next,
//                          AllOneNode child,
//                          AllOneNode parent,
//                          AllOneNode pre,
//                          int val) {
//            this.lead = lead;
//            this.next = next;
//            this.child = child;
//            this.parent = parent;
//            this.pre = pre;
//            this.val = val;
//        }
//        public AllOneNode(int val){
//            this.val = val;
//        }
//        public AllOneNode(){
//        }
//    }
}