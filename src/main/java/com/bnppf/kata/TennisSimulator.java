package com.bnppf.kata;

import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import com.bnppf.kata.models.TennisPlayer;

import java.util.Scanner;

public class TennisSimulator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisInterface tennis = startGameWithTwoPlayers();
        scanner.close();
    }

    private static TennisInterface startGameWithTwoPlayers() {
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
        return new Tennis(new TennisPlayer(firstPlayer) , new TennisPlayer(secondPlayer));
    }
}