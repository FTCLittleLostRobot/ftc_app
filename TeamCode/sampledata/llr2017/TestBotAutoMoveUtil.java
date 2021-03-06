package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-04.
 */

@Autonomous(name = "Test Bot Auto Move Util" , group = "Linear Opmode")
@Disabled
public class TestBotAutoMoveUtil extends LinearOpMode
{
    double rMem;
    double lMem;
    double currentZ;
    //int targetZ = 0;
    double steer;
    double error;
    double rPwr;
    double lPwr;
    double distanceToMove;
    int distanceToMoveInt;
    private double targetHeading = 0.0;
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    
    public void runOpMode()
    {
        robot.init(hardwareMap);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro, robot.right_frnt, robot.right_rear, robot.left_frnt, robot.left_rear, robot.leftlight, this);
        botMove.calibrateGyro();
        botMove.changeMode();
        telemetry.addData("Program Place", "Ready to run");
        telemetry.update();
        waitForStart(); //The code above is init
        botMove.setDist(12);
        botMove.motorPwrs(0.3);
        sleep(500);
        botMove.turnGyro(90 , 0.25);
        sleep(500);
        botMove.turnGyro(-90 , 0.25);
    }

   /* public void turnGyro(int turnDegrees , double speed)
    {
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnDegrees = turnDegrees * ConstUtil.oneDegree;
        targetHeading = gyro.getIntegratedZValue() + turnDegrees;

        if (targetHeading > gyro.getIntegratedZValue()) //If it's more, then you need to turn left
        {
            while (targetHeading > gyro.getIntegratedZValue())
            {
                setMtrPwr(-speed , speed);
                correctHeading(turnDegrees);
            }
            setMtrPwr(0,0);
        }
        else if(gyro.getIntegratedZValue() == targetHeading)
        {
            setMtrPwr(0,0);
        }
        else if (targetHeading < gyro.getIntegratedZValue())
        {
            while (targetHeading < gyro.getIntegratedZValue()) //If it's less, then you need to turn right
            {
                setMtrPwr(speed , -speed);
                correctHeading(turnDegrees);
            }
            setMtrPwr(0,0);
            //correctHeading(turnDegrees);
        }
        setMtrPwr(0,0);
    }
    public void setMtrPwr(double lPower , double rPower)
    {
        robot.right_drive.setPower(rPower);
        robot.left_drive.setPower(lPower);
    }

    public void correctHeading(int tgtHeading)
    {
        if (tgtHeading > gyro.getIntegratedZValue()) //tgtHeading > gyro.getIntegratedZValue() //gyro.getIntegratedZValue() > tgtHeading
        {
            while (tgtHeading > gyro.getIntegratedZValue())
            {
                setMtrPwr(-0.1, 0.1);
            }
        }
        else if (tgtHeading < gyro.getIntegratedZValue()) //tgtHeading < gyro.getIntegratedZValue() //gyro.getIntegratedZValue() < tgtHeading
        {
            while (tgtHeading < gyro.getIntegratedZValue())
            {
                setMtrPwr(0.1, -0.1);
            }
        }
        else
        {
            setMtrPwr(0,0);
        }
        setMtrPwr(0,0);

    }*/
}
