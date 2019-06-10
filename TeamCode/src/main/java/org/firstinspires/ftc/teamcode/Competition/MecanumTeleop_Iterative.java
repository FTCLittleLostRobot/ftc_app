/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoderShiftLeft;
import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoderShiftRight;
import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder;
import org.firstinspires.ftc.teamcode.controllers.ArmDropNoEncoder;
import org.firstinspires.ftc.teamcode.controllers.ArmExtend;
import org.firstinspires.ftc.teamcode.controllers.LanderNoEncoder;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

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

@TeleOp(name="Mecanum: Teleop", group="Mecanum")
public class MecanumTeleop_Iterative extends OpMode{

    /* Declare OpMode members. */
    private HardwareMecanumBase robot = null;
    private ArmDropEncoderShiftRight armDropEncoderShiftRight = null;
    private ArmDropEncoderShiftLeft armDropEncoderShiftLeft = null;
    private ArmDropEncoder armDropEncoderRight = null;
    private ArmDropNoEncoder ArmDropNoEncoder = null;
    private ArmExtend armExtend = null;
    private LanderNoEncoder lander = null;//used for lifting and dropping

    private float starting_left_x = 0;  //this makes the robot strafe right and left
    private float starting_left_y = 0;  //this makes the robot go forwards and backwards
    private float starting_right_x = 0; // this makes the robot rotate
    private boolean ButtonCheck = false;    //left and right bumper; faster, slower
    private Servo servoLeft = null;
    private Servo servoRight = null;
    private boolean servoLeftActive = false;
    private boolean servoLeftDirection = false;
    private boolean servoRightDirection = false;
    private boolean servoLeftDirectionActual = false;
    private boolean servoRightDirectionActual = false;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Step 1: Setup of variables  */
        //armDropEncoderRight = new ArmDropEncoder();
        //armDropEncoderLeft = new ArmDropEncoder();
        armDropEncoderShiftLeft = new ArmDropEncoderShiftLeft();
        armDropEncoderShiftRight = new ArmDropEncoderShiftRight();

        lander = new LanderNoEncoder();
        armExtend = new ArmExtend();
        robot = new HardwareMecanumBase();


        /* Step 2: Setup of hardware  */
        robot.init(hardwareMap);

        /* Step 3: Setup of controllers  */
        lander.init(robot, telemetry);
        this.armExtend.init(robot, telemetry);
        // armDropEncoderLeft.init(robot.ArmDropLeft, telemetry, false);
        //armDropEncoderRight.init(robot.ArmDropRight, telemetry, true);
        //armDropEncoderShift.init(robot.ArmDropRight, telemetry, true);
        armDropEncoderShiftLeft.init(robot.ArmDropLeft, telemetry, false);
        armDropEncoderShiftRight.init(robot.ArmDropRight, telemetry, true);



        servoLeft = robot.servoLeft;
        servoRight = robot.servoRight;

        /* Step 4: Setup of state machines  */
        // NONE

        starting_left_x = -gamepad1.left_stick_x;
        starting_left_y = gamepad1.left_stick_y;
        starting_right_x= -gamepad1.right_stick_x;

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //this shows the robot is ready
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */

    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left_stick_x;
        double left_stick_y;
        double right_stick_x;
        boolean left_bumper;    //boolean means it can only be true or false
        boolean right_bumper;
        //armDropEncoderLeft.ArmDrop(gamepad2.left_stick_y);
        //armDropEncoderRight.ArmDrop(gamepad2.right_stick_y);
        armDropEncoderShiftLeft.ArmDrop(gamepad2.left_stick_y);
        armDropEncoderShiftRight.ArmDrop(gamepad2.right_stick_y);

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left_stick_x = -gamepad1.left_stick_x - starting_left_x;
        left_stick_y = gamepad1.left_stick_y - starting_left_y;
        right_stick_x= -gamepad1.right_stick_x - starting_right_x;
        left_bumper = gamepad1.left_bumper;
        right_bumper= gamepad1.right_bumper;


        // if you hit the right bumper it will go faster if you hit the left bumper it will go slower, both will set it to regular
        if (left_bumper && right_bumper) {
            if (ButtonCheck == false){
                robot.ResetSpeed();
                ButtonCheck = true;
            }
        }
        else if (left_bumper) {
            if (ButtonCheck == false){
                robot.DecreaseSpeed();
                ButtonCheck = true;
            }
        }
        else if (right_bumper) {
            if (ButtonCheck == false) {

                robot.IncreaseSpeed();
                ButtonCheck = true;
            }
        }
        else{
            ButtonCheck = false;
        }

/*
        if (gamepad2.a){
            lander.DoLand();
        }
        else if (gamepad2.y) {
            lander.GoUp();
        }
        else {
            if (robot.lift != null) {
                robot.lift.setPower(0);
            }
        }
*/
        if (robot.ArmExtend != null) {
            if (gamepad2.dpad_up) {
                robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
                armExtend.ExtendingArm(100, 0.1);
            }
            else{
               robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
               robot.ArmExtend.setPower(0);
            }

            if (gamepad2.dpad_down){
                robot.ArmExtend.setDirection(DcMotor.Direction.REVERSE);
                armExtend.ExtendingArm(100, 0.1);
            }
        }

        if (servoLeft != null) {
            if (gamepad2.left_bumper) {
                servoLeftDirectionActual = servoLeftDirection;
                if (servoLeftDirectionActual) {
                    servoLeft.setPosition(servoLeft.getPosition() - (double) 0.4);
                } else {
                    servoLeft.setPosition(servoLeft.getPosition() + (double) 0.4);
                }
                telemetry.addData("Say", "Left Servo is moving");
            } else {
                servoLeftDirection = !servoLeftDirectionActual;
            }
        }

        if (servoRight != null) {
            if (gamepad2.right_bumper) {
                servoRightDirectionActual = servoRightDirection;
                if (servoRightDirectionActual) {
                    servoRight.setPosition(servoRight.getPosition() - (double) 0.5);
                } else {

                    servoRight.setPosition(servoRight.getPosition() + (double) 0.5);
                }
                telemetry.addData("Say", "Right Servo is moving");

            } else {
                servoRightDirection = !servoRightDirectionActual;
            }
        }
        //armDropEncoderLeft.ArmDrop(gamepad2.left_stick_y);
        //armDropEncoderRight.ArmDrop(gamepad2.right_stick_y);
        //    public void init(DcMotor armToMove, Telemetry telemetry, boolean recordReadings){
        //zarmDropEncoderShiftLeft.ArmDrop(gamepad2.right_stick_y);



        robot.MoveMecanum(left_stick_x, left_stick_y, right_stick_x);

        // Send telemetry message to signify robot running;
        //telemetry.addData("Strafe",  "%.2f", left_stick_x);
        //telemetry.addData("foward, back", "%.2f", left_stick_y);
        //telemetry.addData("rotation", "%.2f", right_stick_x);
        //telemetry.addData("left_bumper", left_bumper);
        //telemetry.addData("right_bumper", right_bumper);
        telemetry.addData("SpeedMultplier", robot.SpeedMultiplier);


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
