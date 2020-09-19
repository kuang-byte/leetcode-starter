package com.hao.leetcode.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public final class Helper {

  public static ListNode build(int[] array) {
    ListNode dummy = new ListNode();
    ListNode cur = dummy;
    for (int i : array) {
      cur.next = new ListNode(i);
      cur = cur.next;
    }

    return dummy.next;
  }

  public static void reverse(int[] array, int l, int r) {
    int mid = (l + r) / 2;
    // 0,1,2,3,4
    // l = 1, r = 4 = R - l + 1, mid = 2
    // l = 2, r = 3 = 4 - 2 + 1
    for (int i = l; i < mid; i++) {
      System.out.println("l: " + i + " r-i:" + (r - i));
      int temp = array[i];
      array[i] = array[r - i]; // 2 - 0
      array[r - i] = temp;
    }
  }

  public static void reverse2(int[] array, int l, int r) {
    while (l < r) {
      int temp = array[l];
      array[l] = array[r]; // 2 - 0
      array[r] = temp;

      l++;
      r--;
    }
  }

  public static void print(int[] array) {
    IntStream.of(array).forEach(i -> System.out.print(i + " "));
    System.out.println();
  }

  public static void main(String[] args) {
    Stack<Integer> s = new Stack<>();
    s.push(1);
    s.push(2);
    for (int i : s) {
      System.out.println(i);
    }


  }
}
