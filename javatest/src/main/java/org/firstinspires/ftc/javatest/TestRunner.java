package org.firstinspires.ftc.javatest;

import java.util.Scanner;

public class TestRunner {
    public static void main(String args[]){

        Scanner input = new Scanner(System.in);
        System.out.println("Finding the area of a Circle :D");
        System.out.print("Type in the Radius: ");
        double r = Double.parseDouble(input.nextLine());

        double a = r*r*Math.PI;
        System.out.print("A=" + a);


    }
}