/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ArmDropNoEncoder;

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

@TeleOp(name="TestingMecanum: ArmDrop", group="TestingMecanum")
@Disabled
public class MecanumArmDrop_Iterative extends OpMode{

    /* Declare OpMode members. */
    private HardwareMecanumBase robot       = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware
    private float starting_left_y = 0;  //this makes the robot go forwards and backwards
    private float starting_right_y = 0; // this makes the robot rotate
    private org.firstinspires.ftc.teamcode.controllers.ArmDropNoEncoder ArmDropNoEncoder = new ArmDropNoEncoder();

    public Servo servoLeft = null;
    public Servo servoRight = null;
    boolean servoLeftActive = false;
    boolean servoLeftDirection = false;
    boolean servoRightDirection = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        starting_left_y = -gamepad2.left_stick_y;
        starting_right_y= gamepad2.right_stick_y;
        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        ArmDropNoEncoder.init(robot,telemetry, hardwareMap);
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
        double left_stick_y;
        double right_stick_y;
        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left_stick_y = gamepad2.left_stick_y - starting_left_y;
        right_stick_y = gamepad2.right_stick_y - starting_right_y;


           //robot.ArmDrop(right_stick_y,left_stick_y, telemetry);

           robot.ArmDropRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int newArmDropRightTarget = robot.ArmDropRight.getCurrentPosition();
            //robot.ArmDropRight.setTargetPosition(newArmDropRightTarget);
            //robot.ArmDropRight.setPower(0.75);
           robot.ArmDrop(right_stick_y,left_stick_y,telemetry);
        newArmDropRightTarget = robot.ArmDropRight.getCurrentPosition();
          telemetry.addData("ArmDropTarget", newArmDropRightTarget);
            telemetry.addData("ArmDrop", "Right arm is moving");


        robot.ArmDropLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int newArmDropLeftTarget = robot.ArmDropLeft.getCurrentPosition();
        //robot.ArmDropRight.setTargetPosition(newArmDropRightTarget);
        //robot.ArmDropRight.setPower(0.75);
        robot.ArmDrop(right_stick_y,left_stick_y,telemetry);
        newArmDropLeftTarget = robot.ArmDropLeft.getCurrentPosition();
        telemetry.addData("ArmDropTarget", newArmDropLeftTarget);
        telemetry.addData("ArmDrop", "Left arm is moving");
        telemetry.update();


        

        // Send telemetry message to signify robot running;
        //telemetry.addData("LeftArm", "%.2f", left_stick_y);
        telemetry.addData("RightArm", "%.2f", right_stick_y);

        telemetry.addData("Servo is at", servoLeft.getPosition());
        telemetry.addData("Servo is at", servoRight.getPosition());

        if (gamepad2.left_bumper) {
            if (servoLeftDirection) {
                servoLeft.setPosition(servoLeft.getPosition() - (double)0.01);
            } else
            {
                servoLeft.setPosition(servoLeft.getPosition() + (double)0.01);
            }
            telemetry.addData("Say", "Left Servo is moving");
        }
        else
        {
            servoLeftDirection = !servoLeftDirection;
        }


        if (gamepad2.right_bumper) {
            if (servoRightDirection){
                servoRight.setPosition(servoRight.getPosition() - (double)0.01);
            }else {

                servoRight.setPosition(servoRight.getPosition() + (double)0.01);
            }
            telemetry.addData("Say", "Right Servo is moving");

        }else
        {
            servoRightDirection = !servoRightDirection;
        }


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
