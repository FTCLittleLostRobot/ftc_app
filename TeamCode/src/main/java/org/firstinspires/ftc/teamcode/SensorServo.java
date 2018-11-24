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
    private boolean up = true;
    private boolean DpadDOWN = false;
    private boolean crntDpdDOWN = false;
    private boolean DpadUP = false;
    private boolean crntDpdUP = false;


    public void init()
    {
        servo = hardwareMap.servo.get("servo");

    }


    @Override
    public void loop() {

        telemetry.addData("Servo is at", servo.getPosition());


        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        // send the info back to driver station using telemetry function.
        // if the digital channel returns true it's HIGH and the button is unpressed.
        if (gamepad2.dpad_down) {
            up = false;
            telemetry.addData("Say", "Servo is going clockwise");

        }

        if (gamepad2.dpad_up) {
            up = true;
            telemetry.addData("Say", "Servo is going counter clockwise");

        }

        if (up)
        {
            servo.setPosition(servo.getPosition() + 0.1);

        }
        else
        {
            servo.setPosition(servo.getPosition() - 0.1);
        }
    }

}
