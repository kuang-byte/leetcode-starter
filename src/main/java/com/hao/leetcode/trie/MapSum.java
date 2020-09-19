package com.hao.leetcode.trie;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MapSum {
  /** Initialize your data structure here. */
  private class TrieNode {
    public int val;
    public Map<Character, TrieNode> childs = new HashMap<>();
    public boolean isWord;
  }

  private TrieNode root;

  public MapSum() {
    root = new TrieNode();
  }

  public void insert(String key, int val) {
    TrieNode cur = root;
    for (char c : key.toCharArray()) {
      TrieNode temp = cur.childs.get(c);
      if (temp == null) {
        temp = new TrieNode();
        cur.childs.put(c, temp);
      }

      cur = temp;
    }

    cur.val = val;
    cur.isWord = true;
  }

  public int sum(String prefix) {
    TrieNode cur = root;
    for (char c : prefix.toCharArray()) {
      TrieNode node = cur.childs.get(c);
      if (node == null) {
        return 0;
      }
      cur = node;
    }

    return sum(cur);
  }

  private int sum(TrieNode node) {
    Queue<TrieNode> queue = new ArrayDeque<>();
    queue.add(node);
    int sum = 0;
    while (!queue.isEmpty()) {
      TrieNode temp = queue.poll();
      if (temp == null) continue;
      sum += temp.val;
      queue.addAll(temp.childs.values());
    }

    return sum;
  }
}
