package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Nicholas on 2017-08-04.
 */

@Autonomous(name = "TestGyroCode" , group = "Linear Opmode")
@Disabled
public class  TestGyroCode extends LinearOpMode
{
    double rMem;
    double lMem;
    double currentZ;
    int targetZ = 0;
    double steer;
    double error;
    double rPwr;
    double lPwr;
    double distanceToMove;
    int distanceToMoveInt;
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;
    
    public void runOpMode()
    {
        
        robot.init(hardwareMap);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        changeMode();
        telemetry.addData("->" , "Ready to run");
        telemetry.update();
        waitForStart();
        telemetry.addData("->" , "just started running");
        telemetry.update();
        setDist(12);
        telemetry.addData("->" , "just set distance");
        telemetry.update();
        motorPwrs(0.3);
        telemetry.addData("->" , "just finished running motors");
        telemetry.update();
        correctHeading(targetZ);
        telemetry.addData("->" , "just corrected heading");
        telemetry.update();
        sleep(500);
        telemetry.addData("->" , "just slept");
        telemetry.update();
        turnGyro(90 , 0.2);
        sleep(500);
        turnGyro(-90 , 0.2);
        telemetry.addData("->" , "finished turning");
        telemetry.update();
    }

    public void changeMode()
    {
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setDist(double distanceINCHES)
    {
        distanceToMove = distanceINCHES * ConstUtil.oneInch; //distanceINCHES is a parameter
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = robot.right_rear.getCurrentPosition();
        lMem = robot.left_rear.getCurrentPosition();
        robot.right_rear.setTargetPosition(distanceToMoveInt);
        robot.left_rear.setTargetPosition(distanceToMoveInt);
    }
    public void motorPwrs(double pwr)
    {
        targetZ = gyro.getIntegratedZValue();
        while( (robot.right_rear.getCurrentPosition() < robot.right_rear.getTargetPosition()) && (robot.left_rear.getCurrentPosition() < robot.left_rear.getTargetPosition() ) )
        {
            currentZ = gyro.getIntegratedZValue();
            error = targetZ - currentZ;
            steer = error * ConstUtil.driveCoefficient;
            if ((robot.right_rear.getTargetPosition() < rMem) && (robot.left_rear.getTargetPosition() == lMem))
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
            setMtrPwr(lPwr , rPwr);
            telemetry.addData("Gyro Z" , gyro.getIntegratedZValue());
            telemetry.addData("rmem" , rMem);
            telemetry.addData("lmem" , lMem);
            telemetry.addData("target dist" , distanceToMoveInt);
            telemetry.addData("Current Right" , robot.right_rear.getCurrentPosition());
            telemetry.addData("Current Left" , robot.left_rear.getCurrentPosition());
            telemetry.update();
        }
        setMtrPwr(0,0);
    }

    public void correctHeading(int tgtHeading)
    {
        if (gyro.getIntegratedZValue() > tgtHeading)
        {
            while (gyro.getIntegratedZValue() > tgtHeading)
            {
                setMtrPwr(0.1 , -0.1);
            }
        }
        else if (gyro.getIntegratedZValue() < tgtHeading)
        {
            while (gyro.getIntegratedZValue() < tgtHeading)
            {
                setMtrPwr(-0.1 , 0.1);
            }
        }
        else
        {
            setMtrPwr(0,0);
        }
        setMtrPwr(0,0);

    }

    public void turnGyro(int turnDegrees , double speed)
    {
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;

        if (turnDegrees > gyro.getIntegratedZValue()) //If it's more, then you need to turn right
        {
            while (turnDegrees > gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
                telemetry.addData("Current Z" , gyro.getIntegratedZValue());
                telemetry.addData("TargetZ" , turnDegrees);
                setMtrPwr(0,0);
                sleep(500);
                correctHeading(turnDegrees);
            }
            setMtrPwr(0,0);
        }
        else if(gyro.getIntegratedZValue() == turnDegrees)
        {
            setMtrPwr(0,0);
        }
        else
        {
            while (turnDegrees < gyro.getIntegratedZValue()) //If it's less, then you need to turn left
            {
                setMtrPwr(speed , -speed);
            }
            setMtrPwr(0,0);
            sleep(500);
            correctHeading(turnDegrees);
        }
        setMtrPwr(0,0);
    }
    public void setMtrPwr(double LeftPwr , double RightPwr)
    {
        robot.right_rear.setPower(RightPwr);
        robot.left_rear.setPower(LeftPwr);
    }
}
