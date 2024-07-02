package com.hao.leetcode.array;

public class GameOfLife {

    private static int[][] DIRS = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };

    public void gameOfLife(int[][] board) {
        // 1 -> live
        // 0 -> dead
        // 1 -> 0 if #(neighbors = 1) < 2
        // 1 -> 1 if #(neighbors = 1) = 2 | 3
        // 1 -> 0 if #(neighbors = 1) > 3
        // 0 -> 1 if #(neighbors = 1) = 3
        // simultaneously

        // copy board
        int rows = board.length;
        int cols = board[0].length;

        int[][] copyBoard = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                copyBoard[r][c] = board[r][c];
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = 0;
                for (int[] dir : DIRS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || board[nr][nc] != 1) {
                        continue;
                    }
                    liveNeighbors += board[nr][nc];
                }

                // rule 1 and 3
                if (copyBoard[r][c] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[r][c] = 0;
                }

                // rule 4
                if (copyBoard[r][c] == 0 && liveNeighbors == 3) {
                    board[r][c] = 1;
                }
            }
        }
    }
}
