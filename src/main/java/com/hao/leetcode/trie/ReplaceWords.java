package com.hao.leetcode.trie;

import java.util.List;

public class ReplaceWords {
  public class Trie {

    public TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode cur = root;
      for (char c : word.toCharArray()) {
        if (cur.childs[c - 'a'] == null) {
          cur.childs[c - 'a'] = new TrieNode();
        }

        cur = cur.childs[c - 'a'];
      }

      cur.isLeaf = true;
    }

    public String rootSearch(String word) {
      TrieNode cur = root;
      char[] c = word.toCharArray();
      for (int i = 0; i < c.length; i++) {
        if (cur.childs[c[i] - 'a'] == null) {
          return null;
        }

        if (cur.childs[c[i] - 'a'].isLeaf) {
          return word.substring(0, i + 1);
        }

        cur = cur.childs[c[i] - 'a'];
      }

      return cur.isLeaf ? word : null;
    }
  }

  public class TrieNode {
    public TrieNode[] childs = new TrieNode[26];
    public boolean isLeaf;
  }

  public String replaceWords(List<String> dict, String sentence) {
    Trie trie = new Trie();
    // add dict into tire
    for (String s : dict) {
      trie.insert(s);
    }
    // split the sentence, and search
    String[] words = sentence.split(" ");
    StringBuilder result = new StringBuilder();
    for (String w : words) {
      System.out.println("word " + w);
      String root = trie.rootSearch(w);
      if (root != null) {
        result.append(root).append(" ");
      } else {
        result.append(w).append(" ");
      }
    }

    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }
}
