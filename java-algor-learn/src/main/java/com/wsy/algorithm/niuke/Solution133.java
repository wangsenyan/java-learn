package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution133 {
    int[] seen;
    Map<Integer,Node> map;
    public Node cloneGraph(Node node) {
        //dfs和bfs 拓扑排序类似,深度拷贝???
        this.seen = new int[101];//0 未到达, 1 遍历中 2 遍历完成
        this.map = new HashMap<>();
        return dfs(node);
    }
    private Node dfs(Node node){
        //先复制node
        Node cur = null;
        if(seen[node.val] == 0){//未遍历
            seen[node.val] = 1;//遍历中
            cur = new Node(node.val);
            for (Node neighbor : node.neighbors) {
                cur.neighbors.add(dfs(neighbor));
            }
            map.put(node.val,cur);
            seen[node.val] = 2;
        }else{
            cur = map.get(node.val);
        }
        return cur;
    }
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
