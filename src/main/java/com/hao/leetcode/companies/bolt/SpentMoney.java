package com.hao.leetcode.companies.bolt;


public class SpentMoney {
    /*
     * Say your parent gave you some money to go to the candy store.
     * You want to spend exactly that much to make the most use out of your parent's money.
     * Because you have limited hands, you can only carry four items back with you.
     * You also want to not get the same candy because you'll get sick of it quickly.
     * Given an inventory of the candy store,
     * can you spend the entirety of the money with buying exactly four items?

     Spend exactly same money
     Only carry four items
     can't pick duplicate items
     Method: canSpendAllMoney
     input: inventory of items (price,ID), money given
     output: boolean on if you can spend all money

     Choose items with indexes that are higher than previous avoids duplicates

     money left, index of previously chosen, items left to pick, array of prices

 */

    public boolean canSpendAllMoney(int[] prices, int totalMoney, int numberOfItems) {
        return canSpendAllMoney(0, prices, totalMoney, numberOfItems);
    }

    public boolean canSpendAllMoney(int itemIndex, int[] prices, int totalMoney, int numberOfItems) {
        if (totalMoney == 0 && numberOfItems == 0) return true;
        if (totalMoney < 0 || numberOfItems < 0) return false;

        for (int i = itemIndex; i < prices.length; i++) {
            if (canSpendAllMoney(i + 1, prices, totalMoney - prices[i], numberOfItems--)) {
                return true;
            }
        }

        return false;
    }

    public boolean canSpendAllMoneyNaive(int[] prices, int totalMoney) {
        int size = prices.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int x = j + 1; x < size; x++) {
                    for (int y = x + 1; y < size; y++) {
                        if (prices[i] + prices[j] + prices[x] + prices[y] == totalMoney) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SpentMoney spentMoney = new SpentMoney();
        int[] prices = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(spentMoney.canSpendAllMoney(prices, 6, 3));
    }

//    public static boolean canSpendAllMoneyV2(int moneyLeft, int itemsToPick, int[] prices) {

//        // Build our cache of pairs
//        HashMap<Integer, String> cache = new HashMap<>();
//
//        for (int x = 0; x < prices.length; x++) {
//            for (int i = x + 1; i < prices.length; i++) {
//                Stirng pair = x + "-" + y;
//                int pair = prices[x] + prices[i];
//                cache.add(pair);
//            }
//        }
//
//        // double loops trying all combos
//        for (int x = 0; x < prices.length; x++) {
//            for (int i = x + 1; i < prices.length; i++) {
//                int pair = prices[x] + prices[i];
//                if (cache.contains(moneyLeft - pair)) {
//                    return true;
//                }
//            }
//        }
//
//
//        return false;
//    }
//
//    public class Pair {
//        int x;
//        int y;
//
//        public Pair(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
}