package com.hao.leetcode.companies.bolt;

public class RangeUnionDifference {
    // input: Integer A [a..b], B [c..d] // inclusive

    // interval trees ?

    private static class Pair {
        int start;
        int end;

        public static Pair of(int start, int end) {
            Pair pair = new Pair();
            pair.start = start;
            pair.end = end;
            return pair;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d]", start, end);
        }
    }

    public static Pair union(int[] rangeA, int[] rangeB) {
        // b < c [a..b], [c..d]
        // b == c [a..d]
        // b > c, b < d, [a..d]
        // b > d [a..b] [c..d]
        int a = rangeA[0], b = rangeA[1], c = rangeB[0], d = rangeB[1];
        if (a > b || c > d) {
            return null;
        }

        //   a....b
        //     c.....d
        // c.....d


        //      a....b
        //

        return Pair.of(Math.min(a, c), Math.max(b, d));
    }

    public static Pair difference(int[] rangeA, int[] rangeB) {
        // in rangeA, but not rangeB, left join?
        //       a....b
        //              c....d
        // c...d
        // if a > d || b < c, return rangeA

        int a = rangeA[0], b = rangeA[1], c = rangeB[0], d = rangeB[1];
        if (a > d || b < c) return Pair.of(a, b);
        //  a...b
        //c........d
        if (a >= c && b <= d) return null;
        //      a.....b
        //   c.....d
        //        c......d
        //  Math.max(a,c), Math,min(b,d)
        if (a >= c && a <= d) return Pair.of(d + 1, b);
        if (b <= c && b <= d) return Pair.of(a, c - 1);

        //  a.........b
        //     c....d

        return null;
    }

    public static void main(String[] args) {
        //  union test
        Pair union = RangeUnionDifference.union(new int[]{1, 2}, new int[]{1, 3});
        System.out.printf("expected: [1,3] actual: %s\n", union);
        union = RangeUnionDifference.union(new int[]{1, 10}, new int[]{1, 3});
        System.out.printf("expected: [1,10] actual: %s\n", union);

        // difference test
        Pair difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{9, 10});
        System.out.printf("expected: [1,8] actual: %s\n", difference);
        difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{-1, 0});
        System.out.printf("expected: [1,8] actual: %s\n", difference);
        difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{1, 10});
        System.out.printf("expected: null actual: %s\n", difference);
        difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{1, 8});
        System.out.printf("expected: null actual: %s\n", difference);
        difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{1, 6});
        System.out.printf("expected: [7,8] actual: %s\n", difference);
        difference = RangeUnionDifference.difference(new int[]{1, 8}, new int[]{-1, 7});
        System.out.printf("expected: [8,8] actual: %s\n", difference);
    }

}
