package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas  on 2017-07-17.
 */
@TeleOp(name="GyroStraight", group = "Linear Opmode")

public class GyroStraight extends LinearOpMode
{
    private double distanceToMove = 0;
    private int distanceToMoveInt = 0;
    private double oneInch = 117.79;
    private double driveCoefficient = 0.00001;
    DcMotor rightFront = null;
    DcMotor rightMid = null;
    DcMotor rightRear = null;
    DcMotor leftFront = null;
    DcMotor leftMid = null;
    DcMotor leftRear = null;
    ModernRoboticsI2cGyro Gyro;

    //Combine the 2 set motor power methods into 1 method with parameters for both the right and left side later.
    private void rMotorPower(double power)
    {
        rightFront.setPower(power);
        rightMid.setPower(power);
        rightRear.setPower(power);
    }

    private void lMotorPower(double power)
    {
        leftFront.setPower(power);
        leftMid.setPower(power);
        leftRear.setPower(power);
    }

    private void initMotors()
    {
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMid.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMid.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
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
        distanceToMove = distanceINCHES * oneInch;
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = rightMid.getCurrentPosition();
        lMem = leftMid.getCurrentPosition();
        rightMid.setTargetPosition(distanceToMoveInt);
        leftMid.setTargetPosition(distanceToMoveInt);
            while( (rightMid.getCurrentPosition() < rightMid.getTargetPosition()) || (leftMid.getCurrentPosition() < leftMid.getTargetPosition() ) )
            {
            currentZ = Gyro.getIntegratedZValue();
            error = targetZ - currentZ;
            steer = error * driveCoefficient;
                if ((rightMid.getTargetPosition() < rMem) && (leftMid.getTargetPosition() == lMem))
                {
                    steer = steer * -1;
                }
            rPwr = pwr + steer;
            lPwr = pwr - steer;
                if (rPwr > 1)
                {
                    rPwr = 1;
                }
                    else if (rPwr < -1)
                    {
                        rPwr = 0;
                    }
                if (lPwr > 1)
                {
                    lPwr = 1;
                }
                    else if (lPwr < -1)
                    {
                        lPwr = 0;
                    }
            rMotorPower(rPwr);
            lMotorPower(lPwr);
            telemetry.addData("Tgt Gyro Heading" , targetZ);
            telemetry.addData("Gyro Heading" , currentZ);
            telemetry.addData("R Power" , rightMid.getPower());
            telemetry.addData("Target Position R" , rightMid.getTargetPosition());
            telemetry.addData("Current Position R" , rightMid.getCurrentPosition());
            telemetry.addData("L Power" , leftMid.getPower());
            telemetry.addData("Target Position L" , leftMid.getTargetPosition());
            telemetry.addData("Current Position L" , leftMid.getCurrentPosition());
            telemetry.update();

            }
        rMotorPower(0);
        lMotorPower(0);
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
