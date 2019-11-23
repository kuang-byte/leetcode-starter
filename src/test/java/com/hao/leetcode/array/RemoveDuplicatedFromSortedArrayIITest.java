package com.hao.leetcode.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveDuplicatedFromSortedArrayIITest {

    private RemoveDuplicatedFromSortedArrayII alg;

    @BeforeEach
    void setUp() {
        alg = new RemoveDuplicatedFromSortedArrayII();
    }

    @Test
    void testA() {
        int[] nums = {1, 1, 1, 2, 3};
        int length = alg.removeDuplicates(nums);
        assertEquals(4, length);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, nums);
    }
}