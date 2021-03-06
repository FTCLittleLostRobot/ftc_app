package org.firstinspires.ftc.javatest;

import java.util.Scanner;

public class TicTacToe {
    private void DisplayScreen() {
        System.out.println("Tic Tac Toe! Enter position into textbox! Must be In number ");
    }

    public void StartGame() {
        DisplayScreen();

        GameBoard gameboard = new GameBoard();
        String player = "x";
        do {

            gameboard.DisplayBoard();

            //player takes turn
            Scanner input = new Scanner(System.in);
            int square = Integer.parseInt(input.nextLine());

            gameboard.TicTacTurn(player, square);
            player = "O";
        } while (gameboard.IsGameOver() == false);
        //IsGameOveR
        gameboard.DisplayEndGame();
        /*
        gameboard.GameBoard(
                if grid[][column] = val
        );
        */
    }

    //If false continue with game
    /*
    If 3 in a row
    DisplayEndGame
    */

}

