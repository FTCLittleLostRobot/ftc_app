/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode.Competition;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.Image;


import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.LanderNoEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

/**
 * This file provides basic Telop driving for the testing Mencanum robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Mencanum hardware class to define the devices on the robot.
 * All device access is managed through the HardwareMecanumBase class.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Mecanum: Setup", group="TestingMecanum")

public class MecanumSetup_Teleop extends OpMode {

    /* Declare OpMode members. */
    private HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware

    private boolean ButtonCheck = false;    //left and right bumper; faster, slower
    public DcMotor left_front_drive = null;
    public DcMotor right_front_drive = null;
    public DcMotor left_back_drive = null;
    public DcMotor right_back_drive = null;

  //  private LanderNoEncoder lander = new LanderNoEncoder();    //used for lifting and droping

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");    //this shows the robot is ready

    }

    @Override
    public void loop () {


        if (gamepad1.left_stick_y == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightFrontDrive, -0.3);
            telemetry.addData("Testing:", "right_front");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightFrontDrive, 0);
        }


        if (gamepad1.left_stick_x == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftFrontDrive, -0.3);
            telemetry.addData("Testing:", "left_front");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftFrontDrive, 0);
        }


        if (gamepad1.left_stick_y == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftBackDrive, -0.3);
            telemetry.addData("Testing:", "left_back");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftBackDrive, 0);
        }


        if (gamepad1.left_stick_x == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightBackDrive, -0.3);
            telemetry.addData("Testing:", "right_back");
        }
        else{
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightBackDrive, 0);
        }

/*
        if (gamepad2.a) {
            lander.DoLand();
        }

        if (gamepad2.y) {
            lander.GoUp();
        } else {
            lander.Complete();
        }
*/
    }
}

