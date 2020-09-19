package com.hao.leetcode.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestPairs {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return List.of();

    PriorityQueue<Pair> q = new PriorityQueue<>(Comparator.comparingInt(p -> p.sum));
    for (int n1 : nums1) {
      for (int n2 : nums2) {
        q.add(new Pair(n1, n2));
      }
    }

    System.out.println(q.size());

    List<List<Integer>> ans = new ArrayList<>(k);
    for (int i = Math.min(k, q.size()); i > 0; i--) {
      Pair p = q.poll();
      ans.add(List.of(p.n1, p.n2));
    }

    return ans;
  }

  class Pair {
    int n1, n2, sum;

    Pair(int n1, int n2) {
      this.n1 = n1;
      this.n2 = n2;
      sum = n1 + n2;
    }
  }

  public static void main(String[] args) {
    KthSmallestPairs kthSmallestPairs = new KthSmallestPairs();
    System.out.println(
        kthSmallestPairs.kSmallestPairs(new int[] {1, 1, 2}, new int[] {1, 2, 3}, 10));
  }
}
