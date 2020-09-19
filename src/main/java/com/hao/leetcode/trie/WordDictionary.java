package com.hao.leetcode.trie;

public class WordDictionary {

  private class TrieNode {
    public TrieNode[] childs = new TrieNode[26];
    public boolean isLeaf;
  }

  private TrieNode root;
  /** Initialize your data structure here. */
  public WordDictionary() {
    root = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    TrieNode cur = root;
    for (char c : word.toCharArray()) {
      if (cur.childs[c - 'a'] == null) {
        cur.childs[c - 'a'] = new TrieNode();
      }

      cur = cur.childs[c - 'a'];
    }

    cur.isLeaf = true;
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to
   * represent any one letter.
   */
  public boolean search(String word) {
    TrieNode cur = root;
    return searchHelper(cur, word);
  }

  public boolean searchHelper(TrieNode node, String word) {
    int n = word.length();
    for (int i = 0; i < n; i++) {
      char c = word.charAt(i);
      if (c == '.') {
        // check all the childs
        for (TrieNode t : node.childs) {
          if (t != null && searchHelper(t, word.substring(i + 1))) {
            return true;
          }
        }

        return false;
      } else {
        if (node.childs[c - 'a'] == null) {
          return false;
        } else {
          node = node.childs[c - 'a'];
        }
      }
    }

    return node.isLeaf;
  }
}
