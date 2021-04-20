package com.wsy.basis.juc;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 无锁队列,通过cas
 */
public class UnlockQueue<T> {
    private transient volatile Node<T> head;
    private transient volatile Node<T> tail;
    private final int cap;
    private transient volatile int size;
    UnlockQueue(int cap){
        this.cap = cap;
    }
    public boolean offer(T e){
        //自旋操作
        final Node<T> newNode = new Node<T>(e);
        for(Node<T> t = tail, p = t;;){
            Node<T> q = p.next;
            if(q==null){//tail是尾结点
                if(p.casNext(null,newNode)){//修改成功 p.next = newNode
                    if(p!=t)
                        casTail(t,newNode);//tail = newNode
                    return true;
                }
            }else if(p == q){

            }
        }

    }
    static class Node<T> {
        volatile T item;
        volatile Node<T> prev;
        volatile Node<T> next;
        Node(T item){
           UNSAFE.putObject(this,itemOffset,item);
        }
        //cas替换item
        boolean casItem(T cmp,T val){
            return UNSAFE.compareAndSwapObject(this,itemOffset,cmp,val);
        }
        void lazySetPrev(Node<T> val){
            UNSAFE.putOrderedObject(this,prevOffset,val);
        }
        boolean casPrev(Node<T> cmp, Node<T> val){
            return UNSAFE.compareAndSwapObject(this,prevOffset,cmp,val);
        }
        void lazySetNext(Node<T> val){
            UNSAFE.putOrderedObject(this,nextOffset,val);
        }
        boolean casNext(Node<T> cmp, Node<T> val){
            return UNSAFE.compareAndSwapObject(this,nextOffset,cmp,val);
        }
        private static final Unsafe UNSAFE;
        //private static final sum.misc.Unsafe UNSAFE;
        private static final long itemOffset;//item在Node中的偏移量
        private static final long prevOffset;//prev在Node中的偏移量
        private static final long nextOffset;//next在Node中的偏移量
        static {
            try {
                UNSAFE = Unsafe.getUnsafe();
                Class<?> k = Node.class;
                itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
                prevOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("prev"));
                nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
            } catch (NoSuchFieldException e) {
                throw new Error(e);
            }

        }
    }
    private boolean casHead(Node<T> cmp,Node<T> val){
        return UNSAFE.compareAndSwapObject(this,headOffset,cmp,val);
    }
    private boolean casTail(Node<T> cmp,Node<T> val){
        return UNSAFE.compareAndSwapObject(this,tailOffset,cmp,val);
    }
    private boolean casSize(int cmp,int val){
        return UNSAFE.compareAndSwapInt(this,sizeOffset,cmp,val);
    }
    private boolean casCap(int cmp,int val){
        return UNSAFE.compareAndSwapInt(this,capOffset,cmp,val);
    }
    private static final Unsafe UNSAFE;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long sizeOffset;
    private static final long capOffset;
    static {
        try {
            UNSAFE = Unsafe.getUnsafe();
            Class<?> k = UnlockQueue.class;
            headOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("head"));
            tailOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("tail"));
            sizeOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("size"));
            capOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("cap"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
    public static void main(String[] args) {
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
        ConcurrentLinkedDeque cld = new ConcurrentLinkedDeque();
        cld.add(1);
        cld.add(2);
        cld.remove(1);
    }
}
