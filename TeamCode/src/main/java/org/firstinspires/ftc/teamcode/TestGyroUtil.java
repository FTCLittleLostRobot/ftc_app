package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-04.
 */

@Autonomous(name = "TestGyroUtil" , group = "Linear Opmode")
public class TestGyroUtil extends LinearOpMode
{

    private DcMotor rightMid = null;
    DcMotor leftMid = null;
    ModernRoboticsI2cGyro Gyro;

    public void runOpMode()
    {
        GyroUtil runStuff = new GyroUtil();
        runStuff.changeMode();
        Gyro.calibrate();
        Gyro.resetZAxisIntegrator();
        waitForStart();
        runStuff.setDist(24);
        runStuff.motorPwrs(0.3);
        runStuff.correctHeading();
        sleep(500);
        runStuff.turnGyro(90 , 0.3);
    }
}
