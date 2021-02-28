package com.wsy.algorithm.niuke;

import java.util.*;

class Solution41 {
    private int[][] heights;
    private int row;
    private int col;
    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        this.row = heights.length;
        this.col = heights[0].length;
        //回溯法
        //已经走过的,不能继续走

        boolean[][] pass = new boolean[row][col];
        return dfs(0,0,0,pass);
    }
    private int dfs(int r,int c,int m,boolean[][] pass){
        if(r == row-1 && c == col-1 ) return m;
        //上下左右都运行
        pass[r][c]=true;
        int last = heights[r][c];
        int minOfAll=Integer.MAX_VALUE;
        if(r>0 && !pass[r-1][c]){
            int sub = Math.abs(last-heights[r-1][c]);
            int mx = Math.max(sub,m);
            minOfAll =Math.min(dfs(r-1,c,mx,pass),minOfAll);
        }
        if(c>0 &&!pass[r][c-1]){
            int sub =  Math.abs(last-heights[r][c-1]);
            int mx = Math.max(sub,m);
            minOfAll =Math.min(dfs(r,c-1,mx,pass),minOfAll);
        }
        if(r<row-1 && !pass[r+1][c]){
            int sub = Math.abs(last-heights[r+1][c]);
            int mx = Math.max(sub,m);
            minOfAll =Math.min(dfs(r+1,c,mx,pass),minOfAll);
        }
        if(c<col-1 && !pass[r][c+1]){
            int sub = Math.abs(last-heights[r][c+1]);
            int mx = Math.max(sub,m);
            minOfAll =Math.min(dfs(r,c+1,mx,pass),minOfAll);
        }
        pass[r][c]=false;
        return Math.max(minOfAll,m);
    }
    //求路径广搜
    class Solution411 {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            int left = 0, right = 999999, ans = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                Queue<int[]> queue = new LinkedList<int[]>();
                queue.offer(new int[]{0, 0});
                boolean[] seen = new boolean[m * n];
                seen[0] = true;
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    int x = cell[0], y = cell[1];
                    for (int i = 0; i < 4; ++i) {
                        int nx = x + dirs[i][0];
                        int ny = y + dirs[i][1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                            queue.offer(new int[]{nx, ny});
                            seen[nx * n + ny] = true;
                        }
                    }
                }
                if (seen[m * n - 1]) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

    //并查集
    class Solution412 {
        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            List<int[]> edges = new ArrayList<int[]>();
            for (int i = 0; i < m; ++i) {//初始化边集 [u,v,cost]
                for (int j = 0; j < n; ++j) {
                    int id = i * n + j;
                    if (i > 0) {
                        edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                    }
                    if (j > 0) {
                        edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                    }
                }
            }
            //对每条边进行cost
            Collections.sort(edges, new Comparator<int[]>() {
                public int compare(int[] edge1, int[] edge2) {
                    return edge1[2] - edge2[2];
                }
            });

            UnionFind uf = new UnionFind(m * n);
            int ans = 0;
            for (int[] edge : edges) {
                int x = edge[0], y = edge[1], v = edge[2];
                uf.unite(x, y);//依次把最小边的定点加入,知道最后一个顶点加入
                if (uf.connected(0, m * n - 1)) {
                    ans = v;
                    break;
                }
            }
            return ans;
        }
    }

    // 并查集模板
    class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];//每个节点为独立的连通图
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i) {
                parent[i] = i;//初始化每个节点的父节点为自己
            }
        }

        public int findSet(int x) {
            return parent[x] == x ? x : (parent[x] = findSet(parent[x]));//找到根节点
        }

        public boolean unite(int x, int y) {//合并set
            x = findSet(x);
            y = findSet(y);
            if (x == y) {//同一个set内
                return false;
            }
            if (size[x] < size[y]) {//合并集合,保证size[x]>=size[y]
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;//set数量减1
            return true;
        }

        public boolean connected(int x, int y) {
            x = findSet(x);
            y = findSet(y);
            return x == y;
        }
    }
    //最短路径算法Dj
    class Solution413 {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] edge1, int[] edge2) {
                    return edge1[2] - edge2[2];
                }
            });
            pq.offer(new int[]{0, 0, 0});

            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            boolean[] seen = new boolean[m * n];

            while (!pq.isEmpty()) {
                int[] edge = pq.poll();
                int x = edge[0], y = edge[1], d = edge[2];
                int id = x * n + y;
                if (seen[id]) {
                    continue;
                }
                if (x == m - 1 && y == n - 1) {
                    break;
                }
                seen[id] = true;
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                        dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                        pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                    }
                }
            }
            return dist[m * n - 1];
        }
    }
    public static void main(String[] args) {
        int[][] heights = {{3}};
        System.out.println(new Solution41().minimumEffortPath(heights));
    }
}