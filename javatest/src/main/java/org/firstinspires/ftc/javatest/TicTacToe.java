package org.firstinspires.ftc.javatest;

import java.util.Scanner;

public class TicTacToe {
    private void DisplayScreen() {
        System.out.println("Tic Tac Toe! Enter position into textbox! Must be In number ");
    }

    public void StartGame() {
        DisplayScreen();

        GameBoard gameboard = new GameBoard();
        gameboard.DisplayBoard();

        //player takes turn
        Scanner input = new Scanner(System.in);
        int square = Integer.parseInt(input.nextLine());

        gameboard.TicTacTurn("x", square);


        //IsGameOver
        //If true display : Winner _ Player
        //If false continue with game
    }
}
