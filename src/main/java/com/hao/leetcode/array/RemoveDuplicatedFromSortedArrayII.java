package com.hao.leetcode.array;

public class RemoveDuplicatedFromSortedArrayII {

  public int removeDuplicates(int[] nums) {
    int i = 0, count = 1, n = nums.length;
    while (i < n - 1) {
      if (nums[i] == nums[i + 1]) {
        count++;
        if (count > 2) {
          removeByIndex(nums, i);
          i--;
          n--;
        }
      } else {
        count = 1;
      }
      i++;
    }

    return n;
  }

  private void removeByIndex(int[] nums, int index) {
    for (int i = index; i < nums.length - 1; i++) {
      nums[i] = nums[i + 1];
    }
  }

  public int removeDuplicatesII(int[] nums) {
    int j = 1, counter = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        counter++;
      } else {
        counter = 1;
      }

      if (counter <= 2) {
        nums[j++] = nums[i];
      }
    }

    return j;
  }
}
