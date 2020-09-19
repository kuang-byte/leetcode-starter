package com.hao.leetcode.dp;

public class UniquePathsWithObstacles {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int n = obstacleGrid.length, m = obstacleGrid[0].length;
    int[][] dp = new int[n][m];

    if (obstacleGrid[0][0] == 1) return 0;

    dp[0][0] = 1;
    for (int i = 1; i < m; i++) {
      if (obstacleGrid[0][i] == 1) {
        break;
      }

      dp[0][i] = 1;
    }

    for (int i = 1; i < n; i++) {
      if (obstacleGrid[i][0] == 1) {
        break;
      }
      dp[i][0] = 1;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
      }
    }

    return dp[n - 1][m - 1];
  }
}
