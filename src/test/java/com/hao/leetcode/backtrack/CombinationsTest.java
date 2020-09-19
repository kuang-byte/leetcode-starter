package com.hao.leetcode.backtrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CombinationsTest {
  private Combinations combinations;

  @BeforeEach
  void setUp() {
    combinations = new Combinations();
  }

  @Test
  void testI() {
    var result = combinations.combine(4, 2);
    var expected =
        List.of(
            List.of(2, 4),
            List.of(3, 4),
            List.of(2, 3),
            List.of(1, 2),
            List.of(1, 3),
            List.of(1, 4));
    assertEquals(new HashSet<>(expected), new HashSet<>(result));
  }
}
