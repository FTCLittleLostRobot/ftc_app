package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 Created by Nicholas on 2017-11-03.
 */
@Autonomous(name = "Blue Auto November" , group = "Linear OpMode")
@Disabled
public class AutoNov extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    public void runOpMode()
    {
        robot.init(hardwareMap);
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        robot.glyphElevator.setPower(1);
        sleep(500);
        robot.glyphElevator.setPower(0);
        waitForStart();
        robot.left_frnt.setPower(0.3);
        robot.left_rear.setPower(0.3);
        robot.right_rear.setPower(0.3);
        robot.right_frnt.setPower(0.3);
        sleep(3000);
        robot.left_frnt.setPower(0);
        robot.left_rear.setPower(0);
        robot.right_rear.setPower(0);
        robot.right_frnt.setPower(0);
    }

}
