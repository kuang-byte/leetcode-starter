package com.hao.leetcode.bit;

public class SingleNumberII {
  public int singleNumber(int[] nums) {
    int seenOnce = 0, seenTwice = 0;
    for (int i : nums) {
      seenOnce = ~seenTwice & (seenOnce ^ i);
      System.out.print("seenOnce: " + seenOnce + " ");
      seenTwice = ~seenOnce & (seenTwice ^ i);
      System.out.println("seenTwice: " + seenTwice);
    }

    return seenOnce;
  }

  public static void main(String[] args) {
    System.out.println(new SingleNumberII().singleNumber(new int[] {1, 1, 2, 1}));
  }
}
