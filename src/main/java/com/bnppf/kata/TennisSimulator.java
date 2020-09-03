package com.bnppf.kata;

import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import com.bnppf.kata.models.TennisPlayer;

import java.util.Scanner;

public class TennisSimulator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisInterface tennis = startGameWithTwoPlayers();
        System.out.println("\n Starting New Game!!!!");
        while (tennis.getScore() != null && !tennis.getScore().contains("Winner")) {
            System.out.print("\n Enter Name of player who has won next point --> ");
            String pointScoredByPlayer = scanner.nextLine();
            try {
                tennis.increasePlayerScore(pointScoredByPlayer);
            } catch (TennisException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
            System.out.println("#####################################################");
            System.out.println("Current Score --> " + tennis.getScore());
            System.out.println("#####################################################");
        }
        System.out.println("\n Game over!!!");
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