package com.hao.leetcode.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CombinationsII {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(candidates, target, 0, 0, new ArrayDeque<>(), ans);
    return ans;
  }

  private void backtrack(
      int[] candidates,
      int target,
      int sum,
      int index,
      Deque<Integer> com,
      List<List<Integer>> ans) {
    if (sum > target) return;

    if (sum == target) {
      ans.add(new ArrayList<>(com));
      return;
    }

    // i = index = 0
    // com: [10]
    for (int i = index; i < candidates.length; i++) {
      if (i > index && candidates[i] == candidates[i - 1]) continue;
      com.add(candidates[i]);
      backtrack(candidates, target, sum + candidates[i], i + 1, com, ans);
      com.removeLast();
    }
  }
}
