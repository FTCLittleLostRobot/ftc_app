/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@TeleOp(name = "Sensor: Servo", group = "Iterative OpMode")
@Disabled
public class SensorServo extends OpMode {

    public Servo servoLeft = null;
    public Servo servoRight = null;
    boolean servoLeftActive = false;
    boolean servoLeftDirection = false;
    boolean servoRightDirection = false;
    public void init()
    {
        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");
    }


    @Override
    public void loop() {

        telemetry.addData("Servo is at", servoLeft.getPosition());
        telemetry.addData("Servo is at", servoRight.getPosition());

        if (gamepad2.left_bumper) {
            if (servoLeftDirection) {
                servoLeft.setPosition(servoLeft.getPosition() - (double)0.5);
            } else
            {
                servoLeft.setPosition(servoLeft.getPosition() + (double)0.5);
            }
            telemetry.addData("Say", "Left Servo is moving");
        }
        else
        {
            servoLeftDirection = !servoLeftDirection;
        }


        if (gamepad2.right_bumper) {
            if (servoRightDirection){
                servoRight.setPosition(servoRight.getPosition() - (double)0.5);
            }else {

                servoRight.setPosition(servoRight.getPosition() + (double)0.5);
            }
            telemetry.addData("Say", "Right Servo is moving");

        }else
            {
            servoRightDirection = !servoRightDirection;
            }


        telemetry.addData("Left Servo is at", servoLeft.getPosition());
        telemetry.addData("Right Servo is at", servoRight.getPosition());


    }

}
