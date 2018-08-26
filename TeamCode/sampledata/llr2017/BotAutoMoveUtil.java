package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-08-02.
 */

public class BotAutoMoveUtil
{
    public int counter = 500;
    private double targetHeading = 0.0;
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
    double turnCrntZ = 0;
    double turnStrtZ = 0.0;
    ModernRoboticsI2cGyro gyro = null;
    DcMotor rFrnt = null;
    DcMotor rRear = null;
    DcMotor lFrnt = null;
    DcMotor lRear = null;
    DcMotor lLight = null;
    LinearOpMode currAuto;

    public BotAutoMoveUtil(ModernRoboticsI2cGyro gyro , DcMotor r_Frnt , DcMotor r_Rear , DcMotor l_Frnt , DcMotor l_Rear, DcMotor l_Light, LinearOpMode curOp)
    {
        this.gyro = gyro;
        this.rFrnt = r_Frnt;
        this.lFrnt = l_Frnt;
        this.rRear = r_Rear;
        this.lRear = l_Rear;
        this.lLight = l_Light;
        this.currAuto = curOp;
    }

    public void calibrateGyro()
    {
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
    }

    public void changeMode()
    {
        rRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setDist(double distanceINCHES)
    {
        distanceToMove = distanceINCHES * ConstUtil.oneInch; //distanceINCHES is a parameter
        distanceToMoveInt = (int)Math.round(distanceToMove);
        rMem = rRear.getCurrentPosition();
        lMem = lRear.getCurrentPosition();
        rMemRound = (int)Math.round(rMem);
        lMemRound = (int)Math.round(lMem);
        rRear.setTargetPosition(rMemRound + distanceToMoveInt);
        lRear.setTargetPosition(lMemRound + distanceToMoveInt);
    }
    public void motorPwrs(double pwr)
    {
            targetZ = gyro.getIntegratedZValue();
            if ((rRear.getCurrentPosition() < rRear.getTargetPosition()) && (lRear.getCurrentPosition() < lRear.getTargetPosition()))
            {
                rRear.setDirection(DcMotorSimple.Direction.REVERSE);
                lRear.setDirection(DcMotorSimple.Direction.FORWARD);
                while ((( rRear.getCurrentPosition() < rRear.getTargetPosition()) && (lRear.getCurrentPosition() < lRear.getTargetPosition()))
                        && ! currAuto.isStopRequested() )
                {
                    currentZ = gyro.getIntegratedZValue();
                    if(counter == 500)
                    {
                        error = targetZ - currentZ;
                        steer = error * ConstUtil.driveCoefficient;
                        if ((rRear.getTargetPosition() < rMem) && (lRear.getTargetPosition() == lMem)) {
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

            else if((rRear.getCurrentPosition() > rRear.getTargetPosition()) && (lRear.getCurrentPosition() > lRear.getTargetPosition()))
            {
                while (((rRear.getCurrentPosition() > rRear.getTargetPosition()) && (lRear.getCurrentPosition() > lRear.getTargetPosition()))
                            && ! currAuto.isStopRequested() )
                {
                    currentZ = gyro.getIntegratedZValue();
                    if (counter == 500)
                    {
                        error = targetZ - currentZ;
                        steer = error * ConstUtil.driveCoefficient;
                    /* if ((rRear.getTargetPosition() == rMem) && (lRear.getTargetPosition() < lMem))
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
            correctHeading(targetZ);


    }//mtrPwrs method

    public void correctHeading(double tgtHeading)
    {
        if (tgtHeading > gyro.getIntegratedZValue()) //tgtHeading > gyro.getIntegratedZValue() //gyro.getIntegratedZValue() > tgtHeading
        {
            while ((tgtHeading > gyro.getIntegratedZValue()) && !currAuto.isStopRequested() )
            {
                setMtrPwr(-0.1, 0.1);
            }
        }
        else if (tgtHeading < gyro.getIntegratedZValue()) //tgtHeading < gyro.getIntegratedZValue() //gyro.getIntegratedZValue() < tgtHeading
        {
            while ((tgtHeading < gyro.getIntegratedZValue()) && !currAuto.isStopRequested() )
            {
                setMtrPwr(0.1, -0.1);
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
        turnStrtZ = gyro.getIntegratedZValue();
        rRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;
        targetHeading = turnStrtZ + turnDegrees;
        turnCrntZ = gyro.getIntegratedZValue();

        if (targetHeading > turnCrntZ) //If it's more, then you need to turn left
        {
            while (( targetHeading > turnCrntZ) && !currAuto.isStopRequested() )
            {
                setMtrPwr(-speed , speed);
                turnCrntZ = gyro.getIntegratedZValue();
            }
            setMtrPwr(0,0);
            correctHeading(targetHeading);
        }
        else if(gyro.getIntegratedZValue() == targetHeading)
        {
            setMtrPwr(0,0);
        }
        else if (targetHeading < turnCrntZ)
        {
            while ((targetHeading < turnCrntZ) && !currAuto.isStopRequested() ) //If it's less, then you need to turn right
            {
                setMtrPwr(speed , -speed);
                turnCrntZ = gyro.getIntegratedZValue();

            }
            setMtrPwr(0,0);
            correctHeading(targetHeading);
        }
        setMtrPwr(0,0);
    }
    public void setMtrPwr(double LeftPwr , double RightPwr)
    {
        rFrnt.setPower(RightPwr);
        rRear.setPower(RightPwr);
        lFrnt.setPower(LeftPwr);
        lRear.setPower(LeftPwr);
    }
}
