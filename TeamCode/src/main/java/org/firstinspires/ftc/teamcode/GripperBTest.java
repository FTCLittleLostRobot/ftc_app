package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-10-14.
 */
@TeleOp(name = "Gripper B Test" , group = "Iterative OpMode")

public class GripperBTest extends OpMode
{
    HardwareLLR robot = new HardwareLLR();
    DcMotor gripperB = null;
    private double trigger = 0.0;

    public void init()
    {
        robot.init(hardwareMap);
        gripperB = hardwareMap.dcMotor.get("gripperB");
        gripperB.setDirection(DcMotorSimple.Direction.FORWARD);
        gripperB.setPower(0.0);
        telemetry.addData("Program Place" , "Press the right bumper to open, press the left bumper to close");
        telemetry.update();
    }

    public void loop() //Forward closes the gripper, Reverse opens the gripper. Use a gamepad to set power too
    {
        trigger = gamepad2.right_trigger;
        if (gamepad2.right_bumper)
        {
            if (trigger == 1)
            {
                gripperB.setPower(-0.1);
            }
            else
            {
                gripperB.setPower(-0.7);
            }
        }
        else if(gamepad2.left_bumper)
        {
            if(trigger == 1)
            {
                gripperB.setPower(0.1);
            }
            else
            {
                gripperB.setPower(0.7);
            }
        }
        else
        {
            gripperB.setPower(0.0);
        }
    }

}
