package com.hao.leetcode.companies.bolt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortedWordsInColumn {
    // find words
    // -->

    // sort words

    // print
    // # 7 words, n = 3
    // # 7 / 3 = 2.33333, use 3
    // # 4 / 3 = 1.33333, use 4 / 2

    // # 20 words, n = 4
    // # 20 / 4 = 5, use 4
    // # 16 / 4 = 4, use 4
    // # 12 / 4 = 3, use 4
    // # 8 / 4 = 2, use 4
    // # 4 / 4 = 1, use 4

    // # 7 words, n = 4
    // # 7 / 4 = 1.75, use 7 / 2 = 3
    // # 4 / 4 = 1, use 4


    // if 1 < size(remaining_words) / n < 2
    //    use n
    //
    //
    // - pick n words from the list, and find the max length of it,
    //   add space as padding for the n - 1 words
    // - each col must be as even as possible

    private static class Table {
        List<List<String>> cells;
        int[] maxLengthOfColumns;

        public Table(int rows, int cols) {
            cells = new ArrayList<>(rows);
            for (int i = 0; i < rows; i++) {
                cells.add(new ArrayList<>());
            }
            maxLengthOfColumns = new int[cols];
        }

        public void addInRow(int rowIndex, String value) {
            List<String> cols = cells.get(rowIndex);
            cols.add(value);
            maxLengthOfColumns[cols.size() - 1] = Math.max(maxLengthOfColumns[cols.size() - 1], value.length());
        }

        public void formatPrint() {
            for (int i = 0; i < cells.size(); i++) {
                List<String> row = cells.get(i);
                for (int j = 0; j < row.size(); j++) {
                    String value = row.get(j);
                    String padding = maxLengthOfColumns[j] > value.length() ?
                            " ".repeat(maxLengthOfColumns[j] - value.length()) : "";
                    System.out.print(row.get(j) + padding + " ");
                }
                System.out.println();
            }
        }
    }

    private static LinkedList<String> collectWords(String s) {
        LinkedList<String> words = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
            if (sb.length() > 0) {
                if (c == ' ') {
                    words.add(sb.toString());
                    sb = new StringBuilder();
                }
                if (i == s.length() - 1) {
                    words.add(sb.toString());
                }
            }
        }
        return words;
    }


    private static Table fillWords(LinkedList<String> words, int n) {
        Table table = new Table(n, (int) Math.ceil((float) words.size() / n));
        while (!words.isEmpty()) {
            float halfSize = words.size() / (float) n;
            int numberOfWords = halfSize > 1.0f && halfSize < 2.0f ? words.size() / 2 : n;
            for (int i = 0; i < numberOfWords && !words.isEmpty(); i++) {
                table.addInRow(i, words.poll());
            }
        }
        return table;
    }

    public static void print(String s, int n) {
        LinkedList<String> words = collectWords(s);
        Collections.sort(words);
        Table table = fillWords(words, n);
        table.formatPrint();
    }

    public static void main(String[] args) {
        String s = "  ZeBRa .      T\\;es1t    ..yes elephant GAME pLEAse white";
        int n = 3;
        SortedWordsInColumn.print(s, n);
    }

}
