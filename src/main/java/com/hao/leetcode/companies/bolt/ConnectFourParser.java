package com.hao.leetcode.companies.bolt;

public class ConnectFourParser {
    // 9_r4_brbrbr_3b2rb_b2r2br_r2b3rb

    // _ -> space
    // 3_ -> ___
    // 3b -> bbb
    // brbr -> brbr


    // condition
    // input string may not be valid
    // - fill the board while validating
    // only characters
    // board 6 * 7
    //

    // logic:
    // board[6][7]
    // int x = 0, y = 0;
    // if ( c is digit && c is number) {
    //     int number = 0;
    //     if ( c == _ || c == b || c == r) {
    //          fillPiece(board, x, y, c);
    //     } else {
    //        number = number * 10 + c;
    //     }

    // error cases
    // throw IllegalArgumentException when string contains non digit and non letter number
    // throw IllegalArgumentException when input string contains pieces more than 42

    private char[][] board = new char[6][7];
    private int x, y;
    private int number;

    public char[][] parse(String inputString) {
        char[] input = inputString.toCharArray();
        for (char c : input) {
            if (c != '_' && !Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("Input string contains illegal char: " + c);
            }

            if (c == '_' || c == 'r' || c == 'b') {
                if (number == 0) {
                    fillOnePiece(c);
                } else {
                    fillPieces(c);
                    number = 0;
                }

            } else {
                number = number * 10 + (c - '0');
            }
        }
        return board;
    }

    private void fillOnePiece(char c) {
        // The cursor is out of the board
        if (x == board.length) {
            throw new IllegalArgumentException("Input string contains the number of pieces bigger than the board");
        }
        board[x][y] = c;
        moveCursor();
    }

    private void fillPieces(char c) {
        for (int i = 0; i < number; i++) {
            fillOnePiece(c);
            // The cursor is out of the board
            if (x == board.length && i + 1 < number) {
                throw new IllegalArgumentException("Input string contains the number of pieces bigger than the board");
            }
        }
    }

    private void moveCursor() {
        // move cursor(x,y) to the next available cell
        if (y + 1 < board[0].length) {
            y++;
        } else {
            y = 0;
            x++;
        }
    }

    private static void print(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void assertEqual(char[][] expected, char[][] actual) throws Exception {
        // assert the size of expected char 2d array is equal to actual 2d array¬

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                if (expected[i][j] != actual[i][j]) {
                    System.out.println("Expected char is not equal to actual");
                    System.out.println("Expected: ");
                    print(expected);
                    System.out.println("Actual: ");
                    print(actual);
                    throw new Exception("Not equal");
                }
            }
        }

        System.out.println("Test pass");
    }

    public static void main(String[] args) throws Exception {
//        when_parseConnectFourBoard_succeeds_then_returns();
//        when_parseConnectFourBoard_and_hasInvalidCharacter_then_throws();
        when_parseConnectFourBoard_and_containsPiecesBiggerThan42_then_throws();
    }

    private static void when_parseConnectFourBoard_and_containsPiecesBiggerThan42_then_throws() {
        var connectFourParser = new ConnectFourParser();
//        connectFourParser.parse("43_");
        connectFourParser.parse("b".repeat(42));
    }

    private static void when_parseConnectFourBoard_and_hasInvalidCharacter_then_throws() {
        var connectFourParser = new ConnectFourParser();
        connectFourParser.parse("*");
    }

    public static void when_parseConnectFourBoard_succeeds_then_returns() throws Exception {
        var connectFourParser = new ConnectFourParser();
        char[][] expected = {
                {'_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', 'r', '_', '_', '_', '_'},
                {'b', 'r', 'b', 'r', 'b', 'r', '_'},
                {'b', 'b', 'b', 'r', 'r', 'b', '_'},
                {'b', 'r', 'r', 'b', 'b', 'r', '_'},
                {'r', 'b', 'b', 'r', 'r', 'r', 'b'}
        };
        char[][] result = connectFourParser.parse("9_r4_brbrbr_3b2rb_b2r2br_r2b3rb");
        assertEqual(expected, result);
    }

}
