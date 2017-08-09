package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas  on 2017-07-17.
 */
@Autonomous(name="GyroStraight", group = "Linear Opmode")
@Disabled
public class GyroStraight extends LinearOpMode
{
    private double distanceToMove = 0;
    private int distanceToMoveInt = 0;

    DcMotor rightMid = null;
    DcMotor leftMid = null;
    ModernRoboticsI2cGyro Gyro;

    //Combine the 2 set motor power methods into 1 method with parameters for both the right and left side later.
    private void rMotorPower(double power)
    {
        rightMid.setPower(power);
    }

    private void lMotorPower(double power)
    {
        leftMid.setPower(power);
    }

    private void initMotors()
    {
        rightMid.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMid.setDirection(DcMotorSimple.Direction.FORWARD);
        rMotorPower(0);
        lMotorPower(0);
    }

    private void gyroStraight(double distanceINCHES, double pwr)
    {
        double rMem = 0;
        double lMem = 0;
        double currentZ = 0;
        double targetZ = Gyro.getIntegratedZValue();
        double steer = 0;
        double error = 0;
        double rPwr = 0;
        double lPwr = 0;
        rightMid.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMid.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        distanceToMove = distanceINCHES * ConstUtil.oneInch;
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = rightMid.getCurrentPosition();
        lMem = leftMid.getCurrentPosition();
        rightMid.setTargetPosition(distanceToMoveInt);
        leftMid.setTargetPosition(distanceToMoveInt);



        currentZ = Gyro.getIntegratedZValue();
            if (currentZ > targetZ)
            {
                while (Gyro.getIntegratedZValue() > targetZ)
                {
                    rMotorPower(-0.13);
                    lMotorPower(0.13);
                    telemetry.addData("Tgt Gyro Heading" , targetZ);
                    telemetry.addData("Current Gyro Heading" , Gyro.getIntegratedZValue());
                    telemetry.update();
                }
            }
            else if (currentZ < targetZ)
            {
                while (Gyro.getIntegratedZValue() < targetZ)
                {
                    rMotorPower(0.13);
                    lMotorPower(-0.13);
                    telemetry.addData("Tgt Gyro Heading" , targetZ);
                    telemetry.addData("Current Gyro Heading" , Gyro.getIntegratedZValue());
                    telemetry.update();
                }
            }

        rMotorPower(0);
        lMotorPower(0);



    }

    public void runOpMode()
    {
        Gyro.calibrate();
        Gyro.resetZAxisIntegrator();
        initMotors();
        waitForStart();
        gyroStraight(36 , 0.3);

    }

}
