package com.hao.leetcode.robinhood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static com.hao.leetcode.helper.Helper.assertEquals;

public class MarchOrder {

//    A trade is defined as a string containing the 4 properties given below separated by commas:
//
//    Symbol (4 alphabetical characters, left-padded by spaces)
//    Side (either "B" or "S" for buy or sell)
//    Quantity (4 digits, left-padded by zeros)
//    ID (6 alphanumeric characters)
//    e.g. "AAPL,B,0100,ABC123"
//
//    which represents a trade of a buy of 100 shares of AAPL with ID "ABC123"
//
//    Given two lists of trades - called "house" and "street" trades,
//    write code to create groups of matches between trades and
//    return a list of unmatched house and street trades sorted alphabetically.
//    Without any matching, the output list would contain all elements of both house and street trades.
//    There are many ways to match trades, the first and most important way is an exact match:
//
//    An exact match is a pair of trades containing exactly
//    one house trade and exactly one street trade with identical symbol, side, quantity, and ID
//    Note: Trades are distinct but not unique
//
//    For example, given the following input:
//    house_trades:
//            [ "AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333" ]
//    street_trades:
//            [ " FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123" ]
//    We would expect the following output:
//            [ " FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333" ]
//
//    Because the first (or second) house trade and second street trade form an exact match,
//    leaving behind three unmatched trades.
//
//    Bonus 1 (Test 4 and 5): An attribute match is a match containing
//    exactly one house trade and exactly one street trade
//    with identical symbol, side, and quantity ignoring ID.
//    Prioritize exact matches over attribute matches.
//    Prioritize matching the earliest lexicographical house trade
//    with the earliest lexicographical street trade in case of ties.
//
//    Bonus 2: (Test 6) An offsetting match is a match containing exactly two house trades
//    or exactly two street trades where the symbol and quantity of both trades are the same,
//    but the side is different (one is a buy and one is a sell).
//    Prioritize exact and attribute matches over offsetting matches.
//    Prioritize matching the earliest lexicographical buy with the earliest lexicographical sell.

    public static List<String> matchOrder(List<String> houseTrades, List<String> streetTrades) {
        // load into maps
        Map<String, Integer> houseTradesMap = new HashMap<>();
        Map<String, Integer> streetTradesMap = new HashMap<>();

        for (String house : houseTrades) {
            Integer counter = houseTradesMap.get(house);
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            houseTradesMap.put(house, counter);
        }

        for (String street : streetTrades) {
            Integer counter = streetTradesMap.get(street);
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            streetTradesMap.put(street, counter);
        }

        // exact match
        for (Map.Entry<String, Integer> hEntry : houseTradesMap.entrySet()) {
            Integer streetTradeValue = streetTradesMap.get(hEntry.getKey());
            if (streetTradeValue != null) {
                streetTradesMap.put(hEntry.getKey(), --streetTradeValue);
                hEntry.setValue(hEntry.getValue() - 1);
            }
        }

        // get unmatched orders
        List<String> unmatchedOrders = new ArrayList<>();
        houseTradesMap.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                unmatchedOrders.add(k);
            }
        });
        streetTradesMap.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                unmatchedOrders.add(k);
            }
        });

        return unmatchedOrders;
    }


    private static LinkedHashMap<String, PriorityQueue<String>> getOrderedTradeMap(List<String> trades) {
        LinkedHashMap<String, PriorityQueue<String>> tradesMap = new LinkedHashMap<>();

        for (String trade : trades) {
            String[] properties = trade.split(",");
            String attribute = String.join(",", properties[0], properties[1], properties[2]);
            String id = properties[3];

            PriorityQueue<String> tQueue = tradesMap.get(attribute);
            if (tQueue == null) {
                tQueue = new PriorityQueue<>();
                tradesMap.put(attribute, tQueue);
            }
            tQueue.add(id);
        }

        return tradesMap;
    }

//    Bonus 1 (Test 4 and 5): An attribute match is a match containing
//    exactly one house trade and exactly one street trade
//    with identical symbol, side, and quantity ignoring ID.
//    Prioritize exact matches over attribute matches.
//    Prioritize matching the earliest lexicographical house trade
//    with the earliest lexicographical street trade in case of ties.

    //    Bonus 2: (Test 6) An offsetting match is a match containing exactly two house trades
//    or exactly two street trades where the symbol and quantity of both trades are the same,
//    but the side is different (one is a buy and one is a sell).
//    Prioritize exact and attribute matches over offsetting matches.
//    Prioritize matching the earliest lexicographical buy with the earliest lexicographical sell.
    public static List<String> matchOrderII(List<String> houseTrades, List<String> streetTrades) {
        // load into maps
        Map<String, PriorityQueue<String>> houseTradesMap = getOrderedTradeMap(houseTrades);
        Map<String, PriorityQueue<String>> streetTradesMap = getOrderedTradeMap(streetTrades);

        for (Map.Entry<String, PriorityQueue<String>> hEntry : houseTradesMap.entrySet()) {
            // key = trade with id ignored
            PriorityQueue<String> sQueue = streetTradesMap.get(hEntry.getKey());
            if (sQueue != null) {
                PriorityQueue<String> hQueue = hEntry.getValue();
                // exact match
                for (Iterator<String> hQueueIterator = hQueue.iterator(); hQueueIterator.hasNext(); ) {
                    if (sQueue.remove(hQueueIterator.next())) {
                        hQueueIterator.remove();
                    }
                }

                // attribute match
                // pull one from s and h individually
                while (!sQueue.isEmpty() && !hQueue.isEmpty()) {
                    sQueue.poll();
                    hQueue.poll();
                }
            }
        }

        // offset matching
        for (Map.Entry<String, PriorityQueue<String>> hEntry : houseTradesMap.entrySet()) {
            String[] attributes = hEntry.getKey().split(",");
            String sKey = String.join(",",
                    attributes[0],
                    attributes[1].equals("S") ? "B" : "S",
                    attributes[2]
            );
            PriorityQueue<String> sQueue = streetTradesMap.get(sKey);
            PriorityQueue<String> hQueue = hEntry.getValue();
            if (sQueue != null && sQueue.size() >= 2 && hQueue.size() >= 2) {
                sQueue.poll();
                sQueue.poll();
                hQueue.poll();
                hQueue.poll();
            }
        }

        // get unmatched orders
        List<String> unmatchedOrders = new ArrayList<>();
        houseTradesMap.forEach((k, q) -> q.forEach(v -> unmatchedOrders.add(k + "," + v)));
        streetTradesMap.forEach((k, q) -> q.forEach(v -> unmatchedOrders.add(k + "," + v)));

        return unmatchedOrders;
    }

    public static void main(String[] args) {
        System.out.println("test MatchOrder");
        testMatchOrder();
        System.out.println("test MatchOrderII");
        testMatchOrderII();
    }

    private static void testMatchOrderII() {
        // regular
        List<String> actual = MarchOrder.matchOrderII(List.of("AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333"), List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123"));
        List<String> expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333");
        assertEquals(expected, actual);

        // no matching
        actual = MarchOrder.matchOrderII(List.of("AAPL,B,0100,ABC123"), List.of("FB,B,0100,GBGGGG"));
        expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        // either empty
        actual = MarchOrder.matchOrderII(List.of("AAPL,B,0100,ABC123"), List.of());
        expected = List.of("AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        // either empty
        actual = MarchOrder.matchOrderII(List.of(), List.of("AAPL,B,0100,ABC123"));
        expected = List.of("AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        // regular
        actual = MarchOrder.matchOrderII(
                List.of("AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333"),
                List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC444")
        );
        expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        actual = MarchOrder.matchOrderII(
                List.of("AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333"),
                List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC444", "GOOG,S,0050,CDC555")
        );
        expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC555");
        assertEquals(expected, actual);

        actual = MarchOrder.matchOrderII(
                List.of("AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333", "ROBIN,S,0050,CDC333", "ROBIN,S,0050,CDC333", "ROBIN,S,0050,CDC333"),
                List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC444", "ROBIN,B,0050,CDC555", "ROBIN,B,0050,CDC223")
        );
        expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "ROBIN,S,0050,CDC333");
        assertEquals(expected, actual);
    }

    private static void testMatchOrder() {
        // regular
        List<String> actual = MarchOrder.matchOrder(List.of("AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333"), List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123"));
        List<String> expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333");
        assertEquals(expected, actual);

        // no matching
        actual = MarchOrder.matchOrder(List.of("AAPL,B,0100,ABC123"), List.of("FB,B,0100,GBGGGG"));
        expected = List.of("FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        // either empty
        actual = MarchOrder.matchOrder(List.of("AAPL,B,0100,ABC123"), List.of());
        expected = List.of("AAPL,B,0100,ABC123");
        assertEquals(expected, actual);

        // either empty
        actual = MarchOrder.matchOrder(List.of(), List.of("AAPL,B,0100,ABC123"));
        expected = List.of("AAPL,B,0100,ABC123");
        assertEquals(expected, actual);
    }
}
