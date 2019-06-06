/*
   Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode.Competition;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder;
import org.firstinspires.ftc.teamcode.controllers.ArmExtend;
import org.firstinspires.ftc.teamcode.controllers.LanderNoEncoder;



import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.LanderNoEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

/*
 *   This program should be used to make sure all wires are in and in the correct ports.
 *   If a wire is in the wrong port or not in at all this program will let you know!
 */

@TeleOp(name="Mecanum: Setup", group="TestingMecanum")

public class MecanumSetup_Teleop extends OpMode {

    /* Declare OpMode members. */
    private HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware
    private LanderNoEncoder lander = new LanderNoEncoder();
    private boolean ButtonCheck = false;      //left and right bumper; faster, slower
    private DcMotor left_front_drive = null;   //front left wheel
    private DcMotor right_front_drive = null;  //front right wheel
    private DcMotor left_back_drive = null;    //back left wheel
    private DcMotor right_back_drive = null;   //back right wheel
    private ArmDropEncoder armDropEncoderRight = null;
    private ArmDropEncoder armDropEncoderLeft = null;


    private ArmExtend armExtend = new ArmExtend();

    private void CheckMotor(DcMotor motor, String  motorName)
    {
        // if a motor is not found it will tell the driver which wheel isn't in
        // this helps drivers tell why the program is giving an error and how to fix it easily
        if (motor == null) {
            telemetry.addData("Error", motorName + " not found");
        }
    }

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        armDropEncoderRight = new ArmDropEncoder();
        armDropEncoderLeft = new ArmDropEncoder ();


        robot.init(hardwareMap);
        this.lander.init(robot, telemetry);
        this.armExtend.init(robot, telemetry);
        armDropEncoderRight.init(robot.ArmDropRight, telemetry, true);
        armDropEncoderLeft.init(robot.ArmDropLeft, telemetry, true);



        // Is every motor on correctly? This checks each motor and lift.
        this.CheckMotor(robot.left_front_drive, "left_front");
        this.CheckMotor(robot.right_front_drive, "right_front");
        this.CheckMotor(robot.left_back_drive, "left_back");
            this.CheckMotor(robot.right_back_drive, "right_back");
        this.CheckMotor(robot.lift, "lift");


        telemetry.addData("Say", "Hello Driver");    //this shows the robot is ready

    }

    @Override
    public void loop () {

        armDropEncoderRight.ArmDrop(gamepad2.right_stick_y);
        armDropEncoderLeft.ArmDrop(gamepad2.left_stick_y);



        // this moves the right front wheel; You need to press the left up
        if (gamepad1.left_stick_y == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightFrontDrive, -0.3);
            telemetry.addData("Testing:", "right_front");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightFrontDrive, 0);
        }

        // this moves the left front wheel; You need to press the left stick to the left
        if (gamepad1.left_stick_x == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftFrontDrive, -0.3);
            telemetry.addData("Testing:", "left_front");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftFrontDrive, 0);
        }

        // this moves the left back wheel; You need to press the left stick down
        if (gamepad1.left_stick_y == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftBackDrive, -0.3);
            telemetry.addData("Testing:", "left_back");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftBackDrive, 0);
        }

        // this moves the right back wheel; You need to press the left stick to the right
        if (gamepad1.left_stick_x == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightBackDrive, -0.3);
            telemetry.addData("Testing:", "right_back");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightBackDrive, 0);
        }


        if (gamepad1.a) {
            lander.DoLand();
            telemetry.addData("Testing:", "Bringing robot down; lift up");


        }
        else if (gamepad1.y) {
            lander.GoUp();
            telemetry.addData("Testing:", "Bringing robot up; lift down");

        }
        else {
            lander.Complete();
        }
        if (gamepad2.dpad_up) {
            robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
            armExtend.ExtendingArm(100, 0.1);
            telemetry.addData("Testing:", "Bringing arm up");

        }
        else{
            robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
            robot.ArmExtend.setPower(0);

        }

        if (gamepad2.dpad_down){
            robot.ArmExtend.setDirection(DcMotor.Direction.REVERSE);
            armExtend.ExtendingArm(100, 0.1);
            telemetry.addData("Testing:", "Bringing arm down; pull spring release and push down;");

        }


    }
}

