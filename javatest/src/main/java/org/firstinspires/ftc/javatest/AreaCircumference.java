package org.firstinspires.ftc.javatest;

import java.util.Scanner;

public class AreaCircumference {

    public static void main(String args[]) {


        Scanner input = new Scanner(System.in);
        int x = 0;
        while (x > 3 || x <= 0) {
            System.out.println("Enter '1' for area");
            System.out.println("Enter '2' for circumference");
            System.out.println("Enter '3' for diameter");
            x = Integer.parseInt(input.nextLine());
        }
       /* if (x==1) {
            System.out.println("Finding the area of a Circle :D");
            System.out.print("Type in the Radius: ");
            double r = Double.parseDouble(input.nextLine());


            double a = r*r*Math.PI;
            System.out.print("A=" + a);
        }
        else if (x==2) {
            System.out.println("Finding the circumference of a Circle :D");
            System.out.print("Type in the Radius: ");
            double r = Double.parseDouble(input.nextLine());

            double c = r * 2 * Math.PI;
            System.out.print("C=" + c);
        }

        else if (x==3) {
                System.out.println("Finding the diameter of a Circle :D");
                System.out.print("Type in the Radius: ");
                double r = Double.parseDouble(input.nextLine());

                double d = 2 * r;
                System.out.print("D=" + d);
        }
        else {
                System.out.println("BigChungusEatsCarrots");
            }


        } */
// while (statement) {
//        code
//        code
//    }
// do  {
//        code
//        code
//    } while (condition);
        switch (x)

        {
            case 1:
                System.out.println("Finding the area of a Circle :D");
                System.out.print("Type in the Radius: ");
                double r = Double.parseDouble(input.nextLine());


                double a = r * r * Math.PI;
                System.out.print("A=" + a);
                break;
            case 2:
                System.out.println("Finding the circumference of a Circle :D");
                System.out.print("Type in the Radius: ");
                double r2 = Double.parseDouble(input.nextLine());

                double c = r2 * 2 * Math.PI;
                System.out.print("C=" + c);
                break;
            case 3:
                System.out.println("Finding the diameter of a Circle :D");
                System.out.print("Type in the Radius: ");
                double r3 = Double.parseDouble(input.nextLine());

                double d = 2 * r3;
                System.out.print("D=" + d);
                break;

            default:
                System.out.println("Incorrect Value");
                break;
        }
    }

}