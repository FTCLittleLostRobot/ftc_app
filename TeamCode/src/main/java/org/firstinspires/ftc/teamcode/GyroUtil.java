package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-02. NOW WE CAN ADD THE HARDWARE PART LATER
 */

public class GyroUtil
{

    DcMotor right;
    DcMotor left;
    private ModernRoboticsI2cGyro gyro;
    double rMem;
    double lMem;
    double currentZ;
    double targetZ = gyro.getIntegratedZValue();
    double steer;
    double error;
    double rPwr;
    double lPwr;
    double distanceToMove;
    int distanceToMoveInt;

    void changeMode()
    {
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void setDist(double distanceINCHES)
    {
        distanceToMove = distanceINCHES * ConstUtil.oneInch; //distanceINCHES is a parameter
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = right.getCurrentPosition();
        lMem = left.getCurrentPosition();
        right.setTargetPosition(distanceToMoveInt);
        left.setTargetPosition(distanceToMoveInt);
    }
    void motorPwrs(double pwr)
    {
        while( (right.getCurrentPosition() < right.getTargetPosition()) || (left.getCurrentPosition() < left.getTargetPosition() ) )
        {
            currentZ = gyro.getIntegratedZValue();
            error = targetZ - currentZ;
            steer = error * ConstUtil.driveCoefficient;
            if ((right.getTargetPosition() < rMem) && (left.getTargetPosition() == lMem))
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
        }
        setMtrPwr(0,0);
    }

    void correctHeading()
    {
        if (currentZ > targetZ)
        {
            while (gyro.getIntegratedZValue() > targetZ)
            {
                setMtrPwr(0.13 , -0.13);
            }
        }
        else if (currentZ < targetZ)
        {
            while (gyro.getIntegratedZValue() < targetZ)
            {
                setMtrPwr(-0.13 , 0.13);
            }
        }
        setMtrPwr(0,0);

    }

    void turnGyro(double turnDegrees , double speed)
    {
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;

        if (turnDegrees > gyro.getIntegratedZValue())
        {
            while (turnDegrees > gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
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
