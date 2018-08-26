package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Nicholas on 2017-09-22. This is just to test the gyro on the big robot and look for problems.
 */

@TeleOp(name = "Read Gyro" , group = "Iterative OpMode")
@Disabled
public class ReadGyro extends OpMode
{
    public ModernRoboticsI2cGyro gyro = null;

    public void init()
    {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("->" , "Ready");
        telemetry.update();
    }

    public void loop()
    {
        telemetry.addData("Gyro Z Reading" , gyro.getIntegratedZValue());
        telemetry.update();
    }

}
