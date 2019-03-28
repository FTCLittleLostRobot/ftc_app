package org.firstinspires.ftc.javatest;

import java.util.Scanner;

public class TestRunnerForCaden {
    public static void main(String args[]){
        AnotherClass Hi = new AnotherClass();
        Hi.TestPublicVoid();
        AnotherClass.TestPublicStaticVoid();
        Hi.Mutiply(8, 8);
        int result = Hi.Mutiply(8,8);
        System.out.print(result);

        /**
        System.out.print("Type in FirstNumber: ");
        double FirstNumber = Double.parseDouble(input.nextLine());
        double SecondNumber = Double.parseDouble(input.nextLine());

        double s = FirstNumber * SecondNumber;



        System.out.println("Hello World!");
        //System.out.print("Type in x");
        //int x = Integer.parseInt(input.nextLine());
        //int y = Integer.parseInt(input.nextLine());
        //int z = x - y;





        System.out.print("Type in y1: ");
        double y1 = Double.parseDouble(input.nextLine());
        System.out.print("Type in x1: ");
        double x1 = Double.parseDouble(input.nextLine());
        System.out.print("Type in y2: ");
        double y2 = Double.parseDouble(input.nextLine());
        System.out.print("Type in x2: ");
        double x2 = Double.parseDouble(input.nextLine());

        double s = (y2 - y1) / (x2 - x1);

        System.out.println("Right Front Drive Motor: " + s);
         */
    }
}