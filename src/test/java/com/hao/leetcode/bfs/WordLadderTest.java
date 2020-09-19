package com.hao.leetcode.bfs;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordLadderTest {

  @Test
  void test() {
    WordLadder wordLadder = new WordLadder();
    Assertions.assertEquals(
        5,
        wordLadder.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
  }
}
