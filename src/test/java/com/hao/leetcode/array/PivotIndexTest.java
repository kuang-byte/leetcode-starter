package com.hao.leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PivotIndexTest {

  @Test
  void test() {
    PivotIndex pivotIndex = new PivotIndex();
    int actual = pivotIndex.pivotIndex(new int[] {-1, -1, -1, 0, 1, 1});
    Assertions.assertEquals(0, actual);
  }
}
