package com.hao.leetcode.companies.bolt;

public class SumOfSumOfUniquePaths {
    // final all unique paths back tracking (move down and right only)
    // + them together
    // backtrack
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // 1 2 3 6 9
        // 1 2 5 6 9
        // 1 2 5 8 9
        // 1 4 7 8 9
        // 1 4 5 6 9
        // 1 4 5 8 9

        // 1 1 1
        // 1 2 3
        // 1 3 6

        // 6 3 1
        // 3 3 3
        // 1 3 6

        System.out.println(calculate(grid, 0, 0, grid[0][0]));
    }

    public static int calculate(int[][] grid, int i, int j, int sum) {
        if (i == grid.length || j == grid[0].length) return 0;
        if (i == grid.length - 1 && j == grid[0].length - 1) return sum;

        return (i + 1 < grid.length ? calculate(grid, i + 1, j, sum + grid[i + 1][j]) : 0)
                + (j + 1 < grid[0].length ? calculate(grid, i, j + 1, sum + grid[i][j + 1]) : 0);
    }
}
