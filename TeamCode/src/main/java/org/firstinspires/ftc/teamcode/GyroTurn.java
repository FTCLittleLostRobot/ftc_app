package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-07.
 */

@Autonomous(name = "GyroTurn" , group = "Linear Opmode")

public class GyroTurn extends LinearOpMode
{
    DcMotor rightMid = null;
    DcMotor leftMid = null;
    ModernRoboticsI2cGyro gyro = null;

    public void runOpMode()
    {
        rightMid.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMid.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        waitForStart();
        turnGyro(90,0.3);

    }

    private void turnGyro(double turnDegrees , double speed)
    {
        rightMid.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMid.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;

        if (turnDegrees > gyro.getIntegratedZValue())
        {
            while (turnDegrees > gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
                telemetry.addData("GyroZ" , gyro.getIntegratedZValue());
                telemetry.addData("TargetZ" , turnDegrees);
                telemetry.update();
            }
            setMtrPwr(0,0);
        }
        else if(gyro.getIntegratedZValue() == turnDegrees)
        {
            setMtrPwr(0,0);
        }
        else
        {
            while (turnDegrees < gyro.getIntegratedZValue())
            {
                setMtrPwr(speed , -speed);
                telemetry.addData("GyroZ" , gyro.getIntegratedZValue());
                telemetry.addData("TargetZ" , turnDegrees);
                telemetry.update();
            }
            setMtrPwr(0,0);
        }
    }

    private void setMtrPwr(double left , double right)
    {
        rightMid.setPower(right);
        leftMid.setPower(left);
    }
}
