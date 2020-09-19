package com.hao.leetcode.bfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Trie trie = new Trie();
    for (String s : wordList) {
      trie.insert(s);
    }

    return findMinShortestTransformation(beginWord, endWord, trie, 0, new HashSet<>());
  }

  public class Trie {
    private class TrieNode {
      public TrieNode[] childs = new TrieNode[26];
      public boolean isWord;
    }

    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String s) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); i++) {
        if (cur.childs[s.charAt(i) - 'a'] == null) {
          cur.childs[s.charAt(i) - 'a'] = new TrieNode();
        }

        cur = cur.childs[s.charAt(i) - 'a'];
      }

      cur.isWord = true;
    }

    public boolean search(String s) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); i++) {
        if (cur.childs[s.charAt(i) - 'a'] == null) {
          return false;
        }

        cur = cur.childs[s.charAt(i) - 'a'];
      }

      return cur.isWord;
    }
  }

  private int findMinShortestTransformation(
      String beginWord, String endWord, Trie trie, int level, Set<String> set) {
    if (level >= beginWord.length()) return 0;

    if (beginWord.equals(endWord)) {
      return level;
    }

    char[] letters = beginWord.toCharArray();
    int minLevel = level;
    for (int i = 0; i < letters.length; i++) {
      for (char c = 'a'; c <= 'z'; c++) {
        letters[i] = c;
        String newWord = new String(letters);
        if (trie.search(newWord) && !set.contains(newWord)) {
          set.add(newWord);
          minLevel =
              Math.min(
                  minLevel, findMinShortestTransformation(newWord, endWord, trie, level + 1, set));
        }
        letters[i] = beginWord.charAt(i);
      }
    }

    return minLevel;
  }
}
