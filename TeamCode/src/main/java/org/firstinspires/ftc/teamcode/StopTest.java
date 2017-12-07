package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Nicholas on 2017-12-03.
 */
@Autonomous(name = "Stop Test" , group = "Linear OpMode")
public class StopTest extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();

    public void runOpMode()
    {
        robot.teleInit(hardwareMap);
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        while (opModeIsActive() && !isStopRequested())
        {
            robot.right_frnt.setPower(0.01);
            robot.right_rear.setPower(0.01);
            robot.left_frnt.setPower(0.01);
            robot.left_rear.setPower(0.01);
            telemetry.addData("Program Place" , "Running");
            telemetry.update();
        }
    }
}
