/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@TeleOp(name = "Sensor: Servo", group = "Iterative OpMode")
public class SensorServo extends OpMode {

    public Servo servo = null;


    public void init()
    {
        servo = hardwareMap.servo.get("servo");

    }


    @Override
    public void loop() {

        telemetry.addData("Servo is at", servo.getPosition());


        if (gamepad2.dpad_down) {
            servo.setPosition(servo.getPosition() - (double)0.01);
            telemetry.addData("Say", "Servo is going clockwise");

        }

        if (gamepad2.dpad_up) {
            servo.setPosition(servo.getPosition() + (double)0.01);
            telemetry.addData("Say", "Servo is going counter clockwise");

        }

    }

}
