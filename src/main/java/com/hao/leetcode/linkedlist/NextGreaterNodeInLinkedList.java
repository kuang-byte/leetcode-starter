package com.hao.leetcode.linkedlist;

import com.hao.leetcode.helper.ListNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextGreaterNodeInLinkedList {

  public int[] nextLargerNodesI(ListNode head) {
    List<Integer> result = new ArrayList<>();
    ListNode cur = head;
    while (cur != null) {
      result.add(findNextLargerValue(cur));
      cur = cur.next;
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  public int[] nextLargerNodesII(ListNode head) {
    List<Integer> values = new ArrayList<>();

    ListNode cur = head;
    while (cur != null) {
      values.add(cur.val);
      cur = cur.next;
    }

    int[] result = new int[values.size()];
    Stack<Integer> s = new Stack<>();
    for (int i = values.size() - 1; i >= 0; i--) {
      while (!s.isEmpty() && s.peek() <= values.get(i)) {
        s.pop();
      }

      if (s.isEmpty()) {
        result[i] = 0;
      } else {
        result[i] = s.peek();
      }

      s.push(values.get(i));
    }

    return result;
  }

  private int findNextLargerValue(ListNode head) {
    int value = head.val;
    head = head.next;
    while (head != null && head.val <= value) {
      head = head.next;
    }

    return head == null ? 0 : head.val;
  }

  private ListNode reverse(ListNode head) {
    ListNode prev = null, cur = head;
    while (cur != null) {
      ListNode node = cur.next;
      cur.next = prev;
      prev = cur;
      cur = node;
    }

    return prev;
  }
}
