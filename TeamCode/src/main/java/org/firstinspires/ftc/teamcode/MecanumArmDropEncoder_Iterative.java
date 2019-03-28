/* Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder;

@TeleOp(name="TestingMecanum: ArmDrop Emcoder", group="TestingMecanum")

public class MecanumArmDropEncoder_Iterative extends OpMode{

    /* Declare OpMode members. */
    private HardwareMecanumBase robot       = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware
    private float starting_left_y = 0;  //this makes the robot go forwards and backwards
    private float starting_right_y = 0; // this makes the robot rotate
    private org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder armDropEncoderRight = new ArmDropEncoder();
    private org.firstinspires.ftc.teamcode.controllers.ArmDropEncoder armDropEncoderLeft = new ArmDropEncoder();

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


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        armDropEncoderLeft.init(robot.ArmDropLeft, telemetry, true);
        armDropEncoderRight.init(robot.ArmDropRight, telemetry, false);
        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");
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
        armDropEncoderLeft.ArmDrop(gamepad2.left_stick_y);
        armDropEncoderRight.ArmDrop(gamepad2.right_stick_y);

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
