package com.hao.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The efficiency of only using backtrack is low.
 * Considering it's a recursive solution, one idea is to
 * use memoization. Might be possible to use a 3d array (row, col, DIRECTIONS).
 * The main challenge here is because we don't have a given word which we can leverage the index
 * to know where are right now. But as given a list of words to be searched,
 */
public class WordSearchII {

    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        String word;

        public TrieNode() {
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        // backtrack + trie (fast track if we found a word
        // build Tire tree
        TrieNode root = buildTree(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.child.containsKey(board[i][j])) {
                    findWords(i, j, root, result, board);
                }
            }
        }

        return result;
    }

    private void findWords(int x, int y, TrieNode parent, List<String> result,
                           char[][] board) {
        char letter = board[x][y];
        TrieNode node = parent.child.get(letter);
        if (node.word != null) {
            result.add(node.word);
            // remove duplications
            node.word = null;
        }

        board[x][y] = '#';
        for (int[] d : DIRECTIONS) {
            int row = x + d[0];
            int col = y + d[1];
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
                continue;
            }
            if (node.child.containsKey(board[row][col])) {
                findWords(row, col, node, result, board);
            }
        }

        board[x][y] = letter;
    }


    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                node = node.child.computeIfAbsent(c, ignored -> new TrieNode());
            }
            node.word = w;
        }

        return root;
    }

    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = wordSearchII.findWords(board, words);
        System.out.println(result);
    }
}
