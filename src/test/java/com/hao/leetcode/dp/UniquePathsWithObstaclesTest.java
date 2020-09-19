package com.hao.leetcode.dp;

import org.junit.jupiter.api.Test;

class UniquePathsWithObstaclesTest {

  @Test
  void test() {
    UniquePathsWithObstacles uniquePathsWithObstacles = new UniquePathsWithObstacles();
    int[][] obstacles = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    System.out.println(uniquePathsWithObstacles.uniquePathsWithObstacles(obstacles));
  }
}
