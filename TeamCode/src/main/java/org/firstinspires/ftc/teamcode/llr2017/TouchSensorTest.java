package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Nicholas on 2017-11-10.
 */
@Autonomous(name = "Touch Sensor Test" , group = "Linear OpMode")

public class TouchSensorTest extends LinearOpMode
{
    private DigitalChannel touchSensor = null;
    public void runOpMode()
    {
        touchSensor = hardwareMap.get(DigitalChannel.class, "touchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        waitForStart();
        while (opModeIsActive())
        {
            if(touchSensor.getState() == true)
            {
                telemetry.addData("Touch Sensor State" , "true");
            }
            else
            {
                telemetry.addData("Touch Sensor State" , "false");
            }
            telemetry.update();
        }
    }

}
