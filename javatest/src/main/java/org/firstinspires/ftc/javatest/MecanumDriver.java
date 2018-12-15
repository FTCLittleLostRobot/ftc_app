/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.javatest;
import java.util.Scanner; 

class MecanumDriver {
    public static void Test(){

        Scanner input = new Scanner(System.in);
        System.out.print("Type in X coordinate (-1 to 1): ");
        double x = Double.parseDouble(input.nextLine());
        System.out.print("Type in Y coordinate (-1 to 1): ");
        double y = Double.parseDouble(input.nextLine());
        System.out.print("Type in Rotation value (-1 to 1): ");
        double rotation = Double.parseDouble(input.nextLine());

        double r = Math.hypot( x, y);
        double robotAngle = Math.atan2(y,x) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rotation;
        final double v2 = r * Math.sin(robotAngle) - rotation;
        final double v3 = r * Math.sin(robotAngle) + rotation;
        final double v4 = r * Math.cos(robotAngle) - rotation;

        System.out.println("Left Front Drive Motor:  " + v1);
        System.out.println("Right Front Drive Motor: " + v2);
        System.out.println("Left Back Drive Motor:   " + v3);
        System.out.println("Right Back Drive Motor:  " + v4);
    }
}