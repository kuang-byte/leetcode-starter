package com.hao.leetcode.companies.bolt;

import java.util.HashMap;
import java.util.Map;

public class WordPatternII {

    private Map<Character, String> charToStrMap = new HashMap<>();
    private Map<String, Character> strToCharMap = new HashMap<>();

    public boolean wordPatternMatch(String pattern, String s) {
        // idea
        // try all the possibilities

        // backtrack (recursion)
        // need two maps for 1-1 mapping

        // pattern [X]XXX
        // s       [X]XXXXXXXXXXX
        // pattern [a][b]ab
        // s       [red][b]lueredblue

        // one possibility:
        // a == r, b == e
        //  charToStringMap.get(a) == r != d
        // return false
        // 2nd
        // charToStringMap.get(a) == red != s.substring(4, "red".length()) [inclusive, exclusive]

        // time complexity
        // n!/(n – m)!

        return wordPatternMatch(0, 0, pattern, s);
    }

    private boolean wordPatternMatch(int pIndex, int sIndex, String pattern, String s) {
        if (pIndex == pattern.length() && sIndex == s.length()) return true;
        if (pIndex == pattern.length() || sIndex == s.length()) return false;

        char c = pattern.charAt(pIndex);
        String mappedStr = charToStrMap.get(c);
        if (mappedStr != null) {
            if (!s.startsWith(mappedStr, sIndex)) return false;
            return wordPatternMatch(pIndex + 1, sIndex + mappedStr.length(), pattern, s);
        } else {
            for (int i = sIndex; i < s.length(); i++) {
                String substring = s.substring(sIndex, i + 1);
                if (strToCharMap.containsKey(substring)) continue;
                charToStrMap.put(c, substring);
                strToCharMap.put(substring, c);
                if (wordPatternMatch(pIndex + 1, i + 1, pattern, s)) return true;

                charToStrMap.remove(c);
                strToCharMap.remove(substring);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        WordPatternII solution = new WordPatternII();
//        System.out.println("Actual: " + solution.wordPatternMatch("ab", "cd") + " Expected: true");
        System.out.println("Actual: " + solution.wordPatternMatch("d", "e") + " Expected: true");
    }

}
