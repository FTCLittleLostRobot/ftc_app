//Created by CorruptedCarrotBuunies.exe
package org.firstinspires.ftc.javatest;

public class GameBoard {
    private char[][] grid = new char[3][3];

    public void GameBoard() {
        char val = '1';
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                grid[row][column] = 'X';
                val++;

            }
        }
    }

    public void DisplayBoard() {
        System.out.println( "-----------" );
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
               // System.out.print(grid[row][column]);


                //System.out.print(" X|");
                if (column == 2) {
                    System.out.println(" X");
                }
                else System.out.print(" X |");
                //if 3 x's in a row then next line then next row lines


            }
            System.out.println( "-----------" );

            /*
              | x |
           -----------
              |   |
           -----------
              |   |
             */
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