package com.hao.leetcode.array;

public class PivotIndex {
  public int pivotIndex(int[] nums) {
    // brute force O(n^2)
    // [1,7,3,6,5,6]
    // add from left to right
    // [1,8,11,17,22,28]
    // [28,27,20,17,11,6]
    // [-1,-1,-1,0,1,1]
    // left -> [-1,-2,-3,-3,-2,-1]
    // right ->[-1,0,1,2,1,1]
    // [-1,-1,0,1,1,-1]
    // left -> [-1,-2,0,1,2,1]
    // right -> [-1,0,1,1,0,-1]
    if (nums == null || nums.length == 0) return -1;
    int n = nums.length;
    int[] left = new int[n + 1];
    left[0] = 0;
    for (int i = 1; i < n; i++) {
      left[i] = left[i - 1] + nums[i];
    }

    int[] right = new int[n + 1];
    right[n - 1] = 0;
    for (int i = n - 2; i >= 0; i--) {
      right[i] = right[i + 1] + nums[i];
    }

    if (right[1] == 0) return 0;
    for (int i = 1; i < n; i++) {
      if (left[i - 1] == right[i + 1]) return i;
    }

    return -1;
  }
}
