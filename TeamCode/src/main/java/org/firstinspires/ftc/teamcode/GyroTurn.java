package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-07.
 */

@Autonomous(name = "GyroTurn" , group = "Linear Opmode")
@Disabled
public class GyroTurn extends LinearOpMode
{
    DcMotor right = null;
    DcMotor left = null;
    ModernRoboticsI2cGyro Gyro = null;

    public void runOpMode()
    {
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Gyro.calibrate();
        Gyro.resetZAxisIntegrator();
        waitForStart();
        turnGyro(90,0.3);

    }

    private void turnGyro(double turnDegrees , double speed)
    {
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;

        if (turnDegrees > Gyro.getIntegratedZValue())
        {
            while (turnDegrees > Gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
                telemetry.addData("GyroZ" , Gyro.getIntegratedZValue());
                telemetry.addData("TargetZ" , turnDegrees);
                telemetry.update();
            }
            setMtrPwr(0,0);
        }
        else if(Gyro.getIntegratedZValue() == turnDegrees)
        {
            setMtrPwr(0,0);
        }
        else
        {
            while (turnDegrees < Gyro.getIntegratedZValue())
            {
                setMtrPwr(speed , -speed);
                telemetry.addData("GyroZ" , Gyro.getIntegratedZValue());
                telemetry.addData("TargetZ" , turnDegrees);
                telemetry.update();
            }
            setMtrPwr(0,0);
        }
    }

    private void setMtrPwr(double Left , double Right)
    {
        right.setPower(Right);
        left.setPower(Left);
    }
}
