package com.hao.leetcode.graph;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UnionFind {

    private int[] root;
    // rank to improve union efficiency
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        IntStream.range(0, size).forEach(i -> root[i] = i);
        rank = new int[size];
        Arrays.fill(rank, 1);
    }

    public int find(int x) {
        // path compression
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
