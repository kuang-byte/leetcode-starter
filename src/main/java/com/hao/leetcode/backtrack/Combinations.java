package com.hao.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    backtrack(n, 1, k, new ArrayList<>(), ans);
    return ans;
  }

  private void backtrack(int n, int index, int k, List<Integer> temp, List<List<Integer>> ans) {
    if (temp.size() == k) {
      ans.add(new ArrayList<>(temp));
    } else {
      for (int i = index; i <= n; i++) {
        temp.add(i);
        backtrack(n, i + 1, k, temp, ans);
        temp.remove(temp.size() - 1);
      }
    }
  }
}
