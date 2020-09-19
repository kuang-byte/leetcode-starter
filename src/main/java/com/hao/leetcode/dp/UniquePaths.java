package com.hao.leetcode.dp;

public class UniquePaths {
  public int uniquePaths(int m, int n) {
    int[][] dp = new int[2][m];

    for (int i = 0; i < 2; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i < m; i++) {
      dp[0][i] = 1;
    }

    // m = 1, n = 1
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[1][j] = dp[0][j] + dp[1][j - 1];
      }

      dp[0] = dp[1];
    }

    return dp[1][m - 1];
  }
}
