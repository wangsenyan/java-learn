package com.wsy.algorithm.niuke;

import java.util.*;

class Twitter {
    /** 初始化,使用map保存(用户,贴子列表,时间戳)
     *  使用堆进行保存
     *  使用map保存(用户,关注列表)
     */
    private volatile int time = 0;
    private Map<Integer, List<int[]>> uPost;//用户-发帖
    private Map<Integer, Set<Integer>> uFoll;//用户-关注
    public Twitter() {
       uPost = new HashMap<>();
       uFoll = new HashMap<>();
    }

    /** 创建一条新的推文. */
    public void postTweet(int userId, int tweetId) {
        List<int[]> post = uPost.getOrDefault(userId, new ArrayList<>());
        post.add(new int[]{tweetId,time++});
        uPost.put(userId,post);
    }

    /** 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序. */
    public List<Integer> getNewsFeed(int userId) {
        //每个最多加入10个,然后sort去前10个
        List<int[]> temp = new ArrayList<>();
        Set<Integer> follow = uFoll.getOrDefault(userId, new HashSet<>());
        follow.add(userId);

        //从follow中选择最小的
        for (int uid : follow) {
            List<int[]> post = uPost.getOrDefault(uid, new ArrayList<>());
            int k = 0;
            for (int[] p : post) {
                if( k > 10) break;
                temp.add(p);
                k++;
            }
        }
        temp.sort((o1, o2) -> {
            return o2[1] - o1[1];
        });
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 10 && i < temp.size(); i++) {
            res.add(temp.get(i)[0]);
        }
        return res;
    }

    /** 关注一个用户. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> follower = uFoll.getOrDefault(followerId, new HashSet<>());
        follower.add(followeeId);
        uFoll.put(followerId,follower);
    }

    /** 取消关注一个用户. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> follower = uFoll.getOrDefault(followerId, new HashSet<>());
        follower.remove(followeeId);
        uFoll.put(followerId,follower);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 7);
// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(1);
    }
}