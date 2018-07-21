package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Nicholas on 2017-10-25.
 */
@Autonomous(name = "Init Gripper Test" , group = "Linear OpMode")

public class InitGripperTest extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    public void runOpMode()
    {
        robot.init(hardwareMap);
        waitForStart();
        telemetry.addData("PRogram Place" , "Done");
    }
}
