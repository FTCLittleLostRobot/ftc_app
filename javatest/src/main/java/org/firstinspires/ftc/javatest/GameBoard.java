//Created by CorruptedCarrotBuunies.exe
package org.firstinspires.ftc.javatest;

public class GameBoard {
    private char[][] grid = new char[3][3];

    public GameBoard() {
        char val = '1';
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                grid[row][column] = val;
                val++;

            }
        }
    }

    public void DisplayBoard() {
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                System.out.print(grid[row][column]);
            }
            System.out.println();
        }

    }


    public boolean IsGameOver() {
        return false;
    }

    public void TicTacTurn(String player, int square) {

    }

    public void DisplayEndGame() {

    }
}