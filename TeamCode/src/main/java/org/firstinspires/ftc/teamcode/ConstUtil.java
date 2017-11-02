package org.firstinspires.ftc.teamcode;

/**
 * Created by Nicholas on 2017-08-07.
 */

public class ConstUtil
{
    public static final double deadZone = 0.15; //Teleop
    public static final double spdMulty = 0.5; //Teleop
    public static final double boostCons = 1.0; //Teleop
    public static final double slowMulty = 0.20; //Teleop
    public static final double leftTrigCons = 1.0; //Teleop

    public static final int oneDegree = 1; //Auto gyro turn
    public static final double oneInch = 65.169579812478296099085542308138; //Auto gyro straight WAS 117.79
    public static final double driveCoefficient = 0.00001; //Auto gyro straight

    public static final double rampUpRate = 0.025; //The rate the shooter motor ramps up (Teleop)

    public static final double offRampDist10_27 = 14.0; //10/27/17 Good offRampDist for MedBot. Bot must be placed near front of balance

    public static final double clrServUp = 1.0; //Color Servo Up position
    public static final double clrServDown = 0.0; //Color Servo Down position
    public static final double clrServRate = 0.01; //rate for moving the servo

    //Blue Left angles and distances
    public static final int blueLeftAngL = -12; //Angle for Blue left side left row of the block box WAS 72
    public static final int blueLeftAngC = -46; //Angle for Blue left side center row of the block box
    public static final int blueLeftAngR = -51; //Angle for Blue left side right row of the block box WAS 47

    public static final double blueLeftDistL = 15.0; //Distance to move to Blue left side left row of block box WAS 18.4
    public static final double blueLeftDistC = 16.58; //Distance to move to Blue left side center row of block box
    public static final double blueLeftDistR = 22.0; //Distance to move to Blue left side right row of block box WAS 23

    // Red Right angles and distances, mirror image of Blue Left
    public static final int redRightAngL = 56; //Angle for Red left side left row of the block box WAS 54
    public static final int redRightAngC = 48; //Angle for Red left side center row of the block box WAS 46
    public static final int redRightAngR = 12; //Angle for Red left side right row of the block box

    public static final double redRightDistL = 21.0; //Distance to move to Red right side left row of block box WAS 24
    public static final double redRightDistC = 14.0; //Distance to move to Red right side center row of block box WAS 16.58
    public static final double redRightDistR = 13.0; //Distance to move to Red right side right row of block box was 15

    // Blue Right angles and distances
    public static final double glyBoxColW = 7.63;
    public static final double baseToGlyDist = 16.25; //WAS 16.5
    public static final double after90TurnDist = 11; //Was 14
    public static final double blueRightDistL = baseToGlyDist + (glyBoxColW/2); //Distance to move to Blue Right side left row of block box WAS 18.4
    public static final double blueRightDistC = baseToGlyDist + (glyBoxColW * 1.5); //Distance to move to Blue Right side center row of block box
    public static final double blueRightDistR = baseToGlyDist + (glyBoxColW * 2.5); //Distance to move to Blue RIght side right row of block box WAS 23

    // Red Left angles and distances
    public static final double after90TurnDistRedLft = 11; //Was 14
    public static final double baseToGlyDistRedLeft = -33.5; //WAS 3025
    public static final double redLeftDistL = baseToGlyDistRedLeft - glyBoxColW; //Distance to move to Blue Right side left row of block box WAS 18.4
    public static final double redLeftDistC = baseToGlyDistRedLeft; //Distance to move to Blue Right side center row of block box
    public static final double redLeftDistR = baseToGlyDistRedLeft + glyBoxColW; //Distance to move to Blue RIght side right row of block box WAS 23

}

