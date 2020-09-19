package com.hao.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String s : strs) {
      sb.append(s.length()).append('/').append(s);
    }
    return sb.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {
    List<String> ret = new ArrayList<>();
    int i = 0;
    while (i < s.length()) {
      int slash = s.indexOf('/', i);
      int size = Integer.parseInt(s.substring(i, slash));
      i = slash + size + 1;
      ret.add(s.substring(slash + 1, i));
    }
    return ret;
  }

  public static void main(String[] args) {
    var encodeAndDecodeStrings = new EncodeAndDecodeStrings();
    var encode = encodeAndDecodeStrings.encode(List.of("a/a", "bb"));
    var decode = encodeAndDecodeStrings.decode(encode);
    System.out.println(decode);
  }
}
