package com.hao.leetcode.backtrack;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CombinationsIITest {
  private CombinationsII combinations;

  @BeforeEach
  void setUp() {
    combinations = new CombinationsII();
  }

  @Test
  void testI() {
    var result = combinations.combinationSum2(new int[] {10, 1, 1, 2, 7, 6, 5}, 8);
    var expected = List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6));
    System.out.println(result);
    System.out.println(expected);
  }
}
