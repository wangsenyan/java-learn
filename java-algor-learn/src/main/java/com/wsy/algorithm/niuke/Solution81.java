package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Solution81 {
    private HashMap<Integer,ListIterator<Integer>> map;
    private LinkedList<Integer> list;
    ListIterator<Integer> head;
    ListIterator<Integer> tail;
    public Solution81(){
        map = new HashMap<>();
        list = new LinkedList<>();
        head = list.listIterator();
        tail = list.listIterator();
    }
    public void add(Integer num){
        ListIterator<Integer> it = map.get(num);
        if(it!=null){
            it.remove();
            head.add(num);
            head = list.listIterator();
        }
    }
    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            iterator.remove();
        }

    }
}
