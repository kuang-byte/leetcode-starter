package com.hao.leetcode.priorityqueue;

import com.hao.leetcode.helper.Helper;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
  public int[] topKFrequent(int[] nums, int k) {
    PriorityQueue<Map.Entry<Integer, Integer>> q =
        new PriorityQueue<>(
            // 1 pick e1, -1 pick e2
            (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    Map<Integer, Integer> map = new HashMap<>();
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    q.addAll(map.entrySet());

    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
      ans[i] = q.poll().getKey();
    }

    return ans;
  }

  public static void main(String[] args) {
    TopKFrequent topKFrequent = new TopKFrequent();
    Helper.print(topKFrequent.topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2));
  }
}
