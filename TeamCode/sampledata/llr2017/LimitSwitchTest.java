package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Nicholas on 2017-11-24.
 */
@Autonomous(name = "Limit Switch Test" , group = "Linear OpMode")
@Disabled
public class LimitSwitchTest extends LinearOpMode
{
    DigitalChannel limitSwitch = null;
    public void runOpMode()
    {
        limitSwitch = hardwareMap.get(DigitalChannel.class , "limitSwitch");
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive())
        {
            if(limitSwitch.getState() == true)
            {
                telemetry.addData("Limit Switch" , "In TRUE block");
                telemetry.update();
            }
            else
            {
                telemetry.addData("Limit Swtich" , "In FALSE block");
                telemetry.update();
            }
        }
    }
}
