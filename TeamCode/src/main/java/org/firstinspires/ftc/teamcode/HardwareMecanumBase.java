/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder;

import org.firstinspires.ftc.teamcode.controllers.ArmDropNoEncoder;

/*
 *  Hub 2
 * Motor channel:        "left_front"        motor 0
 * Motor channel:        "right_front"       motor 1
 * Motor channel:        "left_back"         motor 2
 * Motor channel:        "right_back"        motor 3
 *
 *  Hub 1
 * Motor channel:        "lift"              motor1
 *
 *
 * Motors: NeveRest 20 Gearmotor (am-3637)
 *   Theoretical Performance Specifications:
 *   Gearbox Reduction: 19.2:1
 *   Voltage: 12 Volt DC
 *   No Load Free Speed, at gearbox output shaft: 340 RPM
 *   Force Needed to Back-Drive: 6.4 oz-in
 *   Gearbox Output Power: 14 W
 *   Stall Torque: 175 oz-in
 *   Stall Current: 11.5 A
 *   Output counts per revolution of Output Shaft (cpr): 537.6
 *   Output pulse per revolution of encoder shaft (ppr): 134.4

 */
public class HardwareMecanumBase {
    public enum WheelControl {
        LeftFrontDrive,
        RightFrontDrive,
        LeftBackDrive,
        RightBackDrive,
        ArmDropRight,
        ArmDropLeft,
        servoLeft,
        servoRight,
    }

    /* Public OpMode members. */

    public DcMotor left_front_drive = null;
    public DcMotor right_front_drive = null;
    public DcMotor left_back_drive = null;
    public DcMotor right_back_drive = null;
    public DcMotor lift = null;
    public DcMotor ArmExtend = null;
    public DcMotor ArmDropRight = null;
    public DcMotor ArmDropLeft = null;
    public Servo servoLeft = null;
    public Servo servoRight = null;


    /* local OpMode members. */
    public HardwareMap hardwareMap = null;

    private static final double COUNTS_PER_MOTOR_REV = 537.6;  // eg: Countable events per revolution of Output shaft
    private static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private static final double LIFT_GEAR_REDUCTION = 2.0;     // This is < 2.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double LIFT_DIAMETER_INCHES = 0.4;     // For figuring circumference
    public static final double WHEEL_COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    public static final double LIFT_COUNTS_PER_INCH = 8000;
    //public static final double LIFT_COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            //(LIFT_DIAMETER_INCHES * 3.1415);

    private ElapsedTime period = new ElapsedTime();
    public int SpeedMultiplier = 50;

    /* Constructor *private HardwareMecanumBase(){
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hardwareMap = ahwMap;
        //todo i changed the front two motors fron Reverse to Fowards or Fowards to reverse
        // Define and Initialize Motors
        left_front_drive = hardwareMap.tryGet(DcMotor.class, "left_front");
        if (left_front_drive != null) {
            left_front_drive.setDirection(DcMotor.Direction.FORWARD);
        }

        right_front_drive = hardwareMap.tryGet(DcMotor.class, "right_front");
        if (right_front_drive != null){
            right_front_drive.setDirection(DcMotor.Direction.REVERSE);

        }

        left_back_drive = hardwareMap.tryGet(DcMotor.class, "left_back");
        if (left_back_drive != null) {
            left_back_drive.setDirection(DcMotor.Direction.FORWARD);
        }

        right_back_drive = hardwareMap.tryGet(DcMotor.class, "right_back");
        if (right_back_drive != null) {
            right_back_drive.setDirection(DcMotor.Direction.REVERSE);
        }

        lift = hardwareMap.tryGet(DcMotor.class, "lift");
        if (lift != null) {
            lift.setDirection(DcMotor.Direction.FORWARD);
        }
        // need to test not sure if correct

        ArmExtend = hardwareMap.tryGet(DcMotor.class, "ArmExtend");
        if (ArmExtend != null) {
            ArmExtend.setDirection(DcMotor.Direction.FORWARD);
        }

        ArmDropRight = hardwareMap.tryGet(DcMotor.class, "ArmDropRight");
        if (ArmDropRight != null) {
            ArmDropRight.setDirection(DcMotor.Direction.FORWARD);
        }

        ArmDropLeft = hardwareMap.tryGet(DcMotor.class, "ArmDropLeft");
        if (ArmDropLeft != null) {
            ArmDropLeft.setDirection(DcMotor.Direction.FORWARD);
        }

        servoLeft = hardwareMap.tryGet(Servo.class, "servoLeft");
        if (servoLeft != null) {
            servoLeft.setDirection(Servo.Direction.FORWARD);
        }

        servoRight = hardwareMap.tryGet(Servo.class, "servoRight");
        if (servoRight != null) {
            servoRight.setDirection(Servo.Direction.FORWARD);
        }


        ResetMotors();
    }

    public void ResetMotors() {
        // Set all motors to zero power
        if (left_front_drive != null) {
            left_front_drive.setPower(0);
            left_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        if (right_front_drive != null) {
            right_front_drive.setPower(0);
            right_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        if (right_back_drive != null) {
            right_back_drive.setPower(0);
            right_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if (left_back_drive != null) {
            left_back_drive.setPower(0);
            left_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void DrivePower(WheelControl wheel, double power) {

        switch (wheel) {
            case LeftBackDrive:
                left_back_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case RightBackDrive:
                right_back_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case LeftFrontDrive:
                left_front_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case RightFrontDrive:
                right_front_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case ArmDropLeft:
                ArmDropLeft.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case ArmDropRight:
                ArmDropRight.setPower(power * ((double) SpeedMultiplier / 100));
                break;


            default:
                break;

        }
    }

    public int GetWheelSpinDirection(WheelControl wheel, double x, double y, double rotation)
    {
        double r = Math.hypot( x, y);
        double robotAngle = Math.atan2(y,x) - Math.PI / 4;
        double v = 0;

        switch (wheel)
        {
 /*
            case LeftFrontDrive:  //Julia 12-1-6:35 switch left front with right front
                v = r * Math.sin(robotAngle) - rotation;
                break;
            case RightFrontDrive:
                v = r * Math.cos(robotAngle) + rotation;
                break;
*/
            case LeftFrontDrive:
                v = r * Math.cos(robotAngle) + rotation;
                break;

            case RightFrontDrive:
                v = r * Math.sin(robotAngle) - rotation;
                break;

            case LeftBackDrive:
                v = r * Math.sin(robotAngle) + rotation;
                break;

            case RightBackDrive:
                v = r * Math.sin(robotAngle) + rotation;
                break;

            case ArmDropRight:
                v = r * Math.sin(robotAngle) + rotation;
                break;

            case ArmDropLeft:
                v = r * Math.sin(robotAngle) + rotation;
                break;

        }

        if (v >= 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public void MoveMecanum(double x, double y, double rotation)
    {
        double r = Math.hypot( x, y);
        double robotAngle = Math.atan2(y,x) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rotation;
        final double v2 = r * Math.sin(robotAngle) - rotation;
        final double v3 = r * Math.sin(robotAngle) + rotation;
        final double v4 = r * Math.cos(robotAngle) + rotation;

        //DrivePower(WheelControl.LeftFrontDrive, v2);  //Julia 12-1-6:35 switch left front with right front
        //DrivePower(WheelControl.RightFrontDrive, v1);
        DrivePower(WheelControl.LeftFrontDrive, v1);
        DrivePower(WheelControl.RightFrontDrive, v2);
        DrivePower(WheelControl.LeftBackDrive,v3);
        DrivePower(WheelControl.RightBackDrive, v4);
    }

    public void ArmDrop(double x, double y,Telemetry telemetry)
    {
        telemetry.addData("State", "We are in ArmDrop");
        if (x < 0) {
            DrivePower(WheelControl.ArmDropLeft, x );
        }
        else
            DrivePower(WheelControl.ArmDropLeft, x * 0.55);


        if (y < 0){
            DrivePower(WheelControl.ArmDropRight, y );
        }
        else
            DrivePower(WheelControl.ArmDropRight, y * 0.55);

    }


    public void IncreaseSpeed()
    {
        SpeedMultiplier = 70;
    } // high speed

    public void DecreaseSpeed()
    {
        SpeedMultiplier = 20;
    } // low speed

    public void ResetSpeed()
    {
        SpeedMultiplier = 50;
    }// main speed
}

