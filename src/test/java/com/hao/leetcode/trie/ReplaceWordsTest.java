package com.hao.leetcode.trie;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReplaceWordsTest {

  private ReplaceWords replaceWords;

  @BeforeEach
  void setUp() {
    replaceWords = new ReplaceWords();
  }

  @Test
  void test() {
    List<String> roots = List.of("cat", "bat", "rat");
    String sentence = "the cattle was rattled by the battery";
    replaceWords.replaceWords(roots, sentence);
  }
}
