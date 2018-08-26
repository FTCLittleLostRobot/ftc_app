package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 Created by Nicholas on 2017-10-14.
 */
@TeleOp(name = "Gripper B Test" , group = "Iterative OpMode")
@Disabled
public class GripperBTest extends OpMode
{
    HardwareLLR robot = new HardwareLLR();
    private boolean rBmp = false;
    private boolean lBmp = false;
    private int gripperVal = 0;

    public void init()
    {
        robot.init(hardwareMap);
        robot.init(hardwareMap);
        //robot.gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Program Place" , "Press the right bumper to open, press the left bumper to close");
        telemetry.update();
    }

    public void loop() //Forward closes the gripper, Reverse opens the gripper. Use a gamepad to set power too
    {
        if (gamepad2.right_bumper)
        {
            gripperVal = robot.gripperB.getCurrentPosition();
            if(gripperVal > ConstUtil.gripperOutCons)
            {
                robot.gripperB.setPower(-0.15);
            }
            else if(gripperVal <= ConstUtil.gripperOutCons)
            {
                robot.gripperB.setPower(0);
            }
            rBmp = true;
        }
        else if(gamepad2.left_bumper)
        {
           gripperVal = robot.gripperB.getCurrentPosition();
           if(gripperVal < ConstUtil.gripperCloseCons)
           {
               robot.gripperB.setPower(0.15);
           }
           else if(gripperVal >= ConstUtil.gripperCloseCons)
           {
               robot.gripperB.setPower(0.0);
           }
           lBmp = true;
        }
        else
        {
            robot.gripperB.setPower(0.0);
            rBmp = false;
            lBmp = false;
        }
        telemetry.addData("Gripper Encoder" , robot.gripperB.getCurrentPosition());
        telemetry.addData("rBmp" , rBmp);
        telemetry.addData("lBmp" , lBmp);
    }

}
