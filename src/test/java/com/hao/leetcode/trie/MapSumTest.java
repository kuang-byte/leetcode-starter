package com.hao.leetcode.trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapSumTest {

  private MapSum mapSum;

  @BeforeEach
  void setUp() {
    mapSum = new MapSum();
  }

  @Test
  void test() {
    mapSum.insert("apple", 3);
    Assertions.assertEquals(3, mapSum.sum("ap"));
    mapSum.insert("app", 2);
    Assertions.assertEquals(5, mapSum.sum("ap"));
  }

  @Test
  void testB() {
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Integer.MAX_VALUE + 1);
    System.out.println(Integer.MAX_VALUE + 10);
    int total = Integer.MAX_VALUE / 10 - 1;
    System.out.println(total < Integer.MAX_VALUE / 10);
  }
}
