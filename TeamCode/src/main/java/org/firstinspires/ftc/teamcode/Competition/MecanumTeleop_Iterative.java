/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

@TeleOp(name="TestingMecanum: Teleop", group="TestingMecanum")
public class MecanumTeleop_Iterative extends OpMode{

    /* Declare OpMode members. */
    private HardwareMecanumBase robot       = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware
    private float starting_left_x = 0;  //this makes the robot strafe right and left
    private float starting_left_y = 0;  //this makes the robot go forwards and backwards
    private float starting_right_x = 0; // this makes the robot rotate
    private boolean ButtonCheck = false;    //left and right bumper; faster, slower
    private LanderNoEncoder lander    = new LanderNoEncoder();    //used for lifting and droping

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        starting_left_x = -gamepad1.left_stick_x;
        starting_left_y = gamepad1.left_stick_y;
        starting_right_x= -gamepad1.right_stick_x;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        lander.init(robot, telemetry);

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


        if (gamepad2.a){
            lander.DoLand();
        }

        if (gamepad2.y) {
            lander.GoUp();
        }

        else {
            robot.lift.setPower(0);
        }

        robot.MoveMecanum(left_stick_x, left_stick_y, right_stick_x);

        // Send telemetry message to signify robot running;
        telemetry.addData("Strafe",  "%.2f", left_stick_x);
        telemetry.addData("foward, back", "%.2f", left_stick_y);
        telemetry.addData("rotation", "%.2f", right_stick_x);
        telemetry.addData("left_bumper", left_bumper);
        telemetry.addData("right_bumper", right_bumper);
        telemetry.addData("SpeedMultplier", robot.SpeedMultiplier);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}