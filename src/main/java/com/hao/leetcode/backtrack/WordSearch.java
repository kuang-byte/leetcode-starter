package com.hao.leetcode.backtrack;

public class WordSearch {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static boolean exist(char[][] board, String word) {
        // start point 0,0
        // backtrack
        // => up down left right
        // assume the input board is mutable
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(i, j, board, 0, word))
                    return true;
            }
        }

        return false;
    }

    public static boolean exist(int x, int y, char[][] board, int wordIndex, String word) {
        if (wordIndex == word.length()) return true;
        if (x >= board.length || x < 0
                || y >= board[0].length || y < 0
                || word.charAt(wordIndex) != board[x][y])
            return false;

        board[x][y] = '#';
        for (int[] d : DIRECTIONS) {
            if (exist(x + d[0], y + d[1], board, wordIndex + 1, word))
                return true;

        }

        board[x][y] = word.charAt(wordIndex);
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        boolean result = WordSearch.exist(board, "ABCCED");
        System.out.println(result);
    }
}
