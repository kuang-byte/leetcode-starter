package com.hao.leetcode.array;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanJumpTest {

  private CanJump canJump;

  @BeforeEach
  void setUp() {
    canJump = new CanJump();
  }

  @Test
  void test() {
    boolean expected;
    expected = canJump.canJump(new int[] {2, 0, 0});
    assertTrue(expected);
    expected = canJump.canJump(new int[] {3, 2, 1, 0, 4});
    assertFalse(expected);
  }
}
