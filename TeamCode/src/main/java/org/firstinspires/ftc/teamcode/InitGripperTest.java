package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nicholas on 2017-10-25.
 */
@Autonomous(name = "Init Gripper Test" , group = "Linear OpMode")

public class InitGripperTest extends LinearOpMode
{
    private DcMotor gripper = null;
    ElapsedTime initClock = new ElapsedTime();
    public void runOpMode()
    {
        gripper = hardwareMap.dcMotor.get("gripperB");
        gripper.setDirection(DcMotorSimple.Direction.FORWARD);
        gripper.setPower(0.0);
        initClock.reset();
        while (initClock.seconds() < 2.5)
        {
            gripper.setPower(0.7);
            telemetry.addData("Program Place" , "Closing Fast");
            telemetry.update();
        }
        gripper.setPower(0.0);
        initClock.reset();
        while (initClock.seconds() < 6.0)
        {
            gripper.setPower(0.15);
            telemetry.addData("Program Place" , "Closing Slow");
            telemetry.update();
        }
    }
}
