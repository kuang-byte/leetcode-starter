package com.hao.leetcode.companies.bolt;

public class BigInt {
    // https://leetcode.com/problems/multiply-strings/ +
    // https://leetcode.com/problems/add-strings/
    public static String add(String num1, String num2) {
        if (num1 == null || num2 == null)
            return num1 == null ? num2 : num1;

        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = (i < 0 ? 0 : num1.charAt(i) - '0')
                    + (j < 0 ? 0 : num2.charAt(j) - '0')
                    + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);
            i--;
            j--;
        }
        return result.toString();
    }
    //    1 2 <-
    //    1 2 <-
    //    ----
    //    2 4
    //  1 2
    //  1 4 4
    //
    public static String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();
        // length(x * y) <= length(x) + length(y)
        int[] result = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + result[p2];
                result[p1] = sum / 10;
                result[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            if (r == 0 && sb.length() == 0) continue;
            sb.append(r);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {

    }
}
