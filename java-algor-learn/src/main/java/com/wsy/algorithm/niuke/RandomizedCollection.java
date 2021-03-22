package com.wsy.algorithm.niuke;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 */
class RandomizedCollection {
    Map<Integer, Set<Integer>> idx;
    List<Integer> nums;
    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /**
     * 插入val
     * 1. 将val追加到nums尾部  nums.add(val)
     * 2. 将val最后的索引加到 idx.get(val) 的集合中
     *    - Set<Integer> set = idx.getOrDefault(val,new HashSet<Integer>())
     *    - set.add(nums.size()-1)
     * 3. 如果新加入set后长度为1,说明之前没有
     */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /**
     * 删除
     * 1. 从index.get(val) 中选择一个,删掉
     * 2. 将删掉的位置和最后位置元素交换
     * 3. 把被交换的元素的索引集合更改
     *   - 被删的是最后一个,那么不交换
     *   -
     */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) {
            return false;
        }
        //val的set
        Set<Integer> vSet = idx.get(val);
        Integer i = vSet.iterator().next();
        vSet.remove(i);
        //最后一个元素
        int lastNum = nums.get(nums.size() -1);
        //lastNum的set
        Set<Integer> lSet = idx.get(lastNum);
        lSet.remove(nums.size()-1);
        //避免vSet和lSet是一个set
        if(i<nums.size()-1)
            lSet.add(i);
        //交换nums
        nums.set(i,lastNum);
        //删除最后一个元素
        nums.remove(nums.size()-1);
        //如果val的set为空,从idx中删除
        if(vSet.isEmpty()) idx.remove(val);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get((int) (Math.random() * nums.size()));
    }
}