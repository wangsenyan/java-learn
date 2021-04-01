package com.wsy.algorithm.niuke;

public class DoubleLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;//链表长度
    public class Node{
        private Node pre;
        private Node next;
        private E value;
        public Node(Node pre, Node next, E value) {
            this.pre = pre;
            this.next = next;
            this.value = value;
        }
    }
    public Node addFirst(E e){
        Node node = new Node(null, head, e);
        if(head==null){//空链
            head = node;
            tail = node;
        }else{
            head.pre = node;
            head = node;
        }
        size++;
        return node;
    }
    public Node addLast(E e){
        Node node = new Node(tail, null, e);
        if(tail == null){
            tail = node;
            head = node;
        }else{
            tail.next = node;
            tail = node;
        }
        size++;
        return node;
    }

    public Node remove(E e){
        if(size<=0) return null;
        Node cur = head;
        while (cur!=null){
            if(cur.value.equals(e)){
                unlink(cur);
                size--;
                return cur;
            }
        }
        return null;
    }

    public void unlink(Node node){//删除节点
        if(node==null) return;
        Node pre = node.pre;
        Node next = node.next;
        if(pre!=null){
            pre.next = next;
            node.next = null;
        }else{
            if(head==node)//避免node非链表中节点
               head = next;
        }
        if(next!=null){
            next.pre = pre;
            node.pre = null;
        }else{
            if(tail==node)
               tail = pre;
        }
        size--;
    }
    public int size(){
        return size;
    }
    public static void main(String[] args) {
        DoubleLinkedList<Integer> dbList = new DoubleLinkedList<>();
        dbList.addFirst(1);
        DoubleLinkedList.Node node = (DoubleLinkedList.Node) dbList.addFirst(2);
        DoubleLinkedList.Node node1 = (DoubleLinkedList.Node) dbList.addFirst(3);
        dbList.unlink(node);
        dbList.unlink(node1);
    }
}
