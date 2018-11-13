/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.Image;


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

@TeleOp(name="TestingMecanum: Setup", group="TestingMecanum")

public class TestingMecanumSetup_Teleop extends OpMode {

    /* Declare OpMode members. */
    private HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware

    private boolean ButtonCheck = false;    //left and right bumper; faster, slower
    public DcMotor left_front_drive = null;
    public DcMotor right_front_drive = null;
    public DcMotor left_back_drive = null;
    public DcMotor right_back_drive = null;
    MecanumMove moveRobot;

    private LanderNoEncoder lander = new LanderNoEncoder();    //used for lifting and droping

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        moveRobot = new MecanumMove();
    }

    @Override
    public void loop () {
        boolean y;
        boolean x;
        boolean a;
        boolean b;

        if (gamepad1.left_stick_y == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightFrontDrive, 0.3);
            }
        if (gamepad1.left_stick_x == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftFrontDrive, 0.3);
        }

        if (gamepad1.left_stick_y == 1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.LeftBackDrive, 0.3);
        }

        if (gamepad1.left_stick_x == -1) {
            robot.DrivePower(HardwareMecanumBase.WheelControl.RightBackDrive, 0.3);
        }


        if (gamepad2.a) {
            lander.DoLand();
        }

        if (gamepad2.y) {
            lander.GoUp();
        } else {
            robot.lift.setPower(0);
        }

    }
}

