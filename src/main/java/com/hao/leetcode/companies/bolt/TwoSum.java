package com.hao.leetcode.companies.bolt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {

    // Get pairs without duplication
    public List<List<Integer>> twoSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        // double pointer
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            int left = nums[low], right = nums[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                result.add(List.of(nums[low], nums[high]));
                // skip duplicated elements
                while (low < high && nums[low] == left) low++;
                while (low < high && nums[high] == right) right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3};
        TwoSum twoSum = new TwoSum();
        List<List<Integer>> result = twoSum.twoSum(nums, 4);
        System.out.println(result);
    }
}
