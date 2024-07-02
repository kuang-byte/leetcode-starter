package com.hao.leetcode.companies.bolt;

import java.util.HashMap;
import java.util.Map;

public class WordPatternI {

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charToStringMap = new HashMap<>();
        Map<String, Character> stringToCharMap = new HashMap<>();

        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (charToStringMap.containsKey(c)) {
                if (!charToStringMap.get(c).equals(w)) {
                    return false;
                }
            } else {
                if (stringToCharMap.containsKey(w)) {
                    return false;
                } else {
                    charToStringMap.put(c, w);
                    stringToCharMap.put(w, c);
                }
            }
        }

        return true;
    }
}
