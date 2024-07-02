package com.hao.leetcode.companies.bolt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // nums[i], twoSum(nums, target - nums[i])
            List<List<Integer>> twoSumPairs = twoSum(nums, i + 1, target - nums[i]);
            for (List<Integer> pair : twoSumPairs) {
                List<Integer> threeSumPair = new ArrayList<>();
                threeSumPair.add(nums[i]);
                threeSumPair.addAll(pair);
                result.add(threeSumPair);
            }

            // skip duplicate numbers
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }

    // Get pairs without duplication
    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // double pointer
        int low = start, high = nums.length - 1;
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

}
