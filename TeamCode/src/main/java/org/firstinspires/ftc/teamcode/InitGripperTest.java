package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Const;

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
