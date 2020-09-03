package com.bnppf.kata;

import java.util.Scanner;

public class TennisSimulator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String firstPlayer;
        String secondPlayer;
        do {
            System.out.println("Kindly enter two player names to start the game.");
            System.out.println("(Player names cannot be same, empty and null.)");
            System.out.print("Player 1 --> ");
            firstPlayer = scanner.nextLine().trim();
            System.out.print("Player 2 --> ");
            secondPlayer = scanner.nextLine().trim();
        } while ("".equals(firstPlayer) || "".equals(secondPlayer) || firstPlayer.equalsIgnoreCase(secondPlayer));
        scanner.close();
    }
}