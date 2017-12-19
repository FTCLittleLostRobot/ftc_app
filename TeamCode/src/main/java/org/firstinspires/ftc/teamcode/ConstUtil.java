package org.firstinspires.ftc.teamcode;

/**
 * Created by Nicholas on 2017-08-07.
 */

public class ConstUtil
{
    public static final double deadZone = 0.15; //Teleop
    public static final double spdMulty = 0.4; //Teleop
    public static final double boostCons = 1.0; //Teleop
    public static final double slowMulty = 0.33; //Teleop
    public static final double leftTrigCons = 1.0; //Teleop
    public static final int gripCounterCons = 200;

    public static final int elevatorUpCons = 10807; //explained in the name
    public static final int elevatorGripUp = 3641;
    public static final int elevatorDownCons = 0; //explained in the name

    public static final int gripperOutCons = -576; //Constant for moving the gripper out
    public static final int gripperCloseCons = -306; //Constant for closing the gripper to grip a block

    public static final int oneDegree = 1; //Auto gyro turn
    public static final double oneInch = 47.1944458333 ; //Auto gyro straight for Green 4 inch diameter wheel
    public static final double driveCoefficient = 0.00001; //Auto gyro straight

    public static final double rampUpRate = 0.025; //The rate the shooter motor ramps up (Teleop)

    public static final double offRampDist10_27 = 36.0; //WAS 22.0 10/27/17 Good offRampDist for MedBot. Bot must be placed near front of balance WAS 14

    public static final double clrServUp = 1.0; //Color Servo Up position
    public static final double clrServDown = 0.0; //Color Servo Down position
    public static final double clrServRate = 0.01; //rate for moving the servo

    //Blue Left angles and distances
    public static final int blueLeftAngL = -4; //Angle for Blue left side left row of the block box WAS 12
    public static final int blueLeftAngC = -55; //Angle for Blue left side center row of the block box WAS -46
    public static final int blueLeftAngR = -75; //Angle for Blue left side right row of the block box WAS -65

    public static final double blueLeftDistL = 8.0; //Distance to move to Blue left side left row of block box WAS 15
    public static final double blueLeftDistC = 11; //Distance to move to Blue left side center row of block box WAS 16.58
    public static final double blueLeftDistR = 14; //Distance to move to Blue left side right row of block box WAS 10

    // Red Right angles and distances, mirror image of Blue Left
    public static final int redRightAngL = 56; //Angle for Red left side left row of the block box WAS 54
    public static final int redRightAngC = 48; //Angle for Red left side center row of the block box WAS 46
    public static final int redRightAngR = 12; //Angle for Red left side right row of the block box

    //public static final double redRightDistL = 21.0; //Distance to move to Red right side left row of block box WAS 24
    //public static final double redRightDistC = 14.0; //Distance to move to Red right side center row of block box WAS 16.58
    public static final double redRightDistL = 14.0; //Distance to move to Red right side left row of block box WAS 24
    public static final double redRightDistC = 11.0; //Distance to move to Red right side center row of block box WAS 16.58
    //public static final double redRightDistR = 13.0; //Distance to move to Red right side right row of block box was 15
    public static final double redRightDistR = 6.0; //see above lines  for old value, same as mirror BlueLeftDistL

    // Blue Right angles and distances
    public static final double glyBoxColW = 7.63;
    //public static final double baseToGlyDist = 16.25; //WAS 16.5 - 5 inch difference
    public static final double baseToGlyDist = 11.25; //WAS 16.5 was 16.25, see above line
    //public static final double after90TurnDist = 11; //Was 14 - 5 inch difference
    public static final double after90TurnDist = 7; //Was 6 see above comment
    public static final double blueRightDistL = baseToGlyDist + (glyBoxColW/2); //Distance to move to Blue Right side left row of block box WAS 18.4
    public static final double blueRightDistC = baseToGlyDist + (glyBoxColW * 1.5); //Distance to move to Blue Right side center row of block box
    public static final double blueRightDistR = baseToGlyDist + (glyBoxColW * 2.5); //Distance to move to Blue RIght side right row of block box WAS 23

    // Red Left angles and distances
    //public static final double after90TurnDistRedLft = 11; //Was 14  - 5 inch difference
    public static final double after90TurnDistRedLft = 3; //Was 11 see above line
    public static final double baseToGlyDistRedLeft = -33.5; //WAS 3025
    public static final double redLeftDistL = baseToGlyDistRedLeft - glyBoxColW; //Distance to move to Blue Right side left row of block box WAS 18.4
    public static final double redLeftDistC = baseToGlyDistRedLeft; //Distance to move to Blue Right side center row of block box
    public static final double redLeftDistR = baseToGlyDistRedLeft + glyBoxColW; //Distance to move to Blue RIght side right row of block box WAS 23

}

