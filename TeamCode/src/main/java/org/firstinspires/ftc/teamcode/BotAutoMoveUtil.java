package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-08-02.
 */

public class BotAutoMoveUtil
{
    public int counter = 500;
    double rMem;
    double lMem;
    int rMemRound = 0;
    int lMemRound = 0;
    double currentZ;
    double targetZ = 0.0;
    double steer;
    double error;
    double rPwr;
    double lPwr;
    double distanceToMove;
    int distanceToMoveInt;
    ModernRoboticsI2cGyro gyro = null;
    DcMotor rDrive = null;
    DcMotor lDrive = null;

    public BotAutoMoveUtil(ModernRoboticsI2cGyro gyro , DcMotor r_Drive , DcMotor l_Drive)
    {
        this.gyro = gyro;
        this.rDrive = r_Drive;
        this.lDrive = l_Drive;
    }

    public void calibrateGyro()
    {
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
    }

    public void changeMode()
    {
        rDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setDist(double distanceINCHES)
    {
        distanceToMove = distanceINCHES * ConstUtil.oneInch; //distanceINCHES is a parameter
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = rDrive.getCurrentPosition();
        lMem = lDrive.getCurrentPosition();
        rMemRound = (int)Math.round(rMem);
        lMemRound = (int)Math.round(lMem);
        rDrive.setTargetPosition(rMemRound + distanceToMoveInt);
        lDrive.setTargetPosition(lMemRound + distanceToMoveInt);
    }
    public void motorPwrs(double pwr)
    {
        targetZ = gyro.getIntegratedZValue();
        if ((rDrive.getCurrentPosition() < rDrive.getTargetPosition()) && (lDrive.getCurrentPosition() < lDrive.getTargetPosition()))
        {
            rDrive.setDirection(DcMotorSimple.Direction.FORWARD);
            lDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            while ((rDrive.getCurrentPosition() < rDrive.getTargetPosition()) && (lDrive.getCurrentPosition() < lDrive.getTargetPosition()))
            {
                currentZ = gyro.getIntegratedZValue();
                if(counter == 500)
                {
                    error = targetZ - currentZ;
                    steer = error * ConstUtil.driveCoefficient;
                    if ((rDrive.getTargetPosition() < rMem) && (lDrive.getTargetPosition() == lMem)) {
                        steer = steer * -1;
                    }
                    rPwr = pwr + steer;
                    lPwr = pwr - steer;
                    if (rPwr > 1) {
                        rPwr = 1;
                    } else if (rPwr < -1) {
                        rPwr = 0;
                    }
                    if (lPwr > 1) {
                        lPwr = 1;
                    } else if (lPwr < -1) {
                        lPwr = 0;
                    }
                    setMtrPwr(lPwr, rPwr);
                    counter = 1;
                }
                counter = counter + 1;
            }//while loop
            setMtrPwr(0, 0);
        }//if statement

        else if((rDrive.getCurrentPosition() > rDrive.getTargetPosition()) && (lDrive.getCurrentPosition() > lDrive.getTargetPosition()))
        {
            while ((rDrive.getCurrentPosition() > rDrive.getTargetPosition()) && (lDrive.getCurrentPosition() > lDrive.getTargetPosition()))
            {
                currentZ = gyro.getIntegratedZValue();
                if (counter == 500)
                {
                    error = targetZ - currentZ;
                    steer = error * ConstUtil.driveCoefficient;
                    /* if ((rDrive.getTargetPosition() == rMem) && (lDrive.getTargetPosition() < lMem))
                    {
                        steer = steer * -1;
                    }*/
                    rPwr = -pwr - steer; //notice how these are switched from forwards code. This is because the robot is "facing"
                    lPwr = -pwr + steer; //the other way.
                    if (rPwr > 0)
                    {
                        rPwr = 0;
                    }
                    else if (rPwr < -1)
                    {
                        rPwr = -1;
                    }

                    if (lPwr > 0)
                    {
                        lPwr = 0;
                    }
                    else if (lPwr < -1)
                    {
                        lPwr = -1;
                    }
                    setMtrPwr(lPwr, rPwr);
                    counter = 1;
                }
                counter = counter + 1;
            }//while loop
            setMtrPwr(0,0);
        }
        counter = 500;
    }//mtrPwrs method

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
        rDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;

        if (turnDegrees > gyro.getIntegratedZValue()) //If it's more, then you need to turn right
        {
            while (turnDegrees > gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
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
            correctHeading(turnDegrees);
        }
        setMtrPwr(0,0);
    }
    public void setMtrPwr(double LeftPwr , double RightPwr)
    {
        rDrive.setPower(RightPwr);
        lDrive.setPower(LeftPwr);
    }
}
