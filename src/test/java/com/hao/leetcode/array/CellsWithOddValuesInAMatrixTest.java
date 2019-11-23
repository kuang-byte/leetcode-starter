package com.hao.leetcode.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellsWithOddValuesInAMatrixTest {

    CellsWithOddValuesInAMatrix subject;

    @BeforeEach
    void setUp() {
        subject = new CellsWithOddValuesInAMatrix();
    }

    @Test
    void oddCells() {
        int result = subject.oddCells(2, 3, new int[][]{{0, 1}, {1, 1}});
        assertEquals(6, result);
    }
}