package com.hao.leetcode.linkedlist;

import com.hao.leetcode.helper.Helper;
import com.hao.leetcode.helper.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NextGreaterNodeInLinkedListTest {

    private NextGreaterNodeInLinkedList alg;

    @BeforeEach
    void setUp() {
        alg = new NextGreaterNodeInLinkedList();
    }

    @Test
    void testII_0() {
        ListNode head = Helper.build(new int[]{2, 1, 5});
        int[] result = alg.nextLargerNodesII(head);
        int[] expected = {5, 5, 0};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testII_1() {
        ListNode head = Helper.build(new int[]{2, 7, 4, 3, 5});
        int[] result = alg.nextLargerNodesII(head);
        int[] expected = {7, 0, 5, 5, 0};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testII_2() {
        ListNode head = Helper.build(new int[]{1, 7, 5, 1, 9, 2, 5, 1});
        int[] result = alg.nextLargerNodesII(head);
        int[] expected = {7, 9, 9, 9, 0, 5, 0, 0};
        Assertions.assertArrayEquals(expected, result);
    }
}