package com.hao.leetcode.array;


public class CellsWithOddValuesInAMatrix {

    public int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < indices.length; i++) {
            int ri = indices[i][0];
            int ci = indices[i][1];
            // increment on row r
            for (int c = 0; c < m; c++) {
                matrix[ri][c]++;
            }
            // increment on column ci
            for (int r = 0; r < n; r++) {
                matrix[r][ci]++;
            }
        }

        int totalNumberOfOddNumbers = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (matrix[r][c] % 2 != 0) {
                    totalNumberOfOddNumbers++;
                }
            }
        }

        return totalNumberOfOddNumbers;
    }
}
