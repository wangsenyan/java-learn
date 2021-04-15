package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
      public interface NestedInteger {
          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
      }
    private ArrayList<Integer> stack;
    private int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayList<>();
        index = 0;
        it(nestedList);
    }
    private void it(List<NestedInteger> nestedList){
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger ni = nestedList.get(i);
            if(ni.isInteger()) stack.add(ni.getInteger());
            else it(ni.getList());
        }
    }
    @Override
    public Integer next() {
       return stack.get(index++);
    }

    @Override
    public boolean hasNext() {
       return index == stack.size();
    }
}