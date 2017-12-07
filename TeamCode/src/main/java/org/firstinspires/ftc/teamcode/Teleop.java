package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nicholas on 2017-08-05.
 */
@TeleOp(name = "Teleop" , group = "Iterative Opmode")
public class Teleop extends OpMode
{

    double rStk;
    double lStk;
    double boost;
    double lTrig;
    double rMtrPwr;
    double lMtrPwr;
    //private boolean resetCheck = false;
    private boolean gripPower = false;
    //ModernRoboticsI2cGyro gyro = null;

    HardwareLLR robot = new HardwareLLR();

    public void init()
    {
     robot.teleInit(hardwareMap);
     /*gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
     gyro.calibrate();
     gyro.resetZAxisIntegrator();*/
     telemetry.addData("Program Place" , "Press the right bumper to open, press the left bumper to close");
     telemetry.update();
     telemetry.addData("Status" , "Ready");
     telemetry.update();
    }

    public void start()
    {
        robot.gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        rStk = gamepad1.right_stick_y*-1;
        lStk = gamepad1.left_stick_y*-1;
        boost = gamepad1.right_trigger;
        lTrig = gamepad1.left_trigger;

        if(gamepad2.x)
        {
            gripPower = true;
            robot.gripperB.setPower(0.01);
        }
        else
        {
            gripPower = false;
        }

        if (gamepad2.right_bumper)
        {
            if(gripPower)
            {

            }
            else
            {
                robot.gripperB.setPower(-0.15);
            }

        }
        else if(gamepad2.left_bumper)
        {
            if(gripPower)
            {

            }
            else
            {
                robot.gripperB.setPower(0.15);
            }
        }
        else
        {
            if(gripPower)
            {

            }
            else
            {
                robot.gripperB.setPower(0.0);
            }
        }

        if (gamepad2.dpad_up)
        {
            robot.glyphElevator.setPower(1);
        }
        else if(gamepad2.dpad_down)
        {
            robot.glyphElevator.setPower(-1);
        }
        else
        {
            robot.glyphElevator.setPower(0.0);
        }

        if (lTrig == ConstUtil.leftTrigCons)
        {
            rMtrPwr = rStk * ConstUtil.slowMulty;
            lMtrPwr = lStk * ConstUtil.slowMulty;
        }
        else
        {
            if (boost == ConstUtil.boostCons )
            {
                rMtrPwr = rStk;
                lMtrPwr = lStk;
            }
            else
            {
                rMtrPwr = rStk * ConstUtil.spdMulty;
                lMtrPwr = lStk * ConstUtil.spdMulty;
            }
        }

        setMtrPwr(lMtrPwr , rMtrPwr);
        telemetry.addData("Right Frnt Motor Power" , robot.right_frnt.getPower());
        telemetry.addData("Right Rear Motor Power" , robot.right_rear.getPower());
        telemetry.addData("Right Rear Motor POSITION" , robot.right_rear.getCurrentPosition());
        telemetry.addData("Left Frnt Motor Power" , robot.left_frnt.getPower());
        telemetry.addData("Left Rear Motor Power" , robot.left_rear.getPower());
        telemetry.addData("Left Rear Motor POSITION" , robot.left_rear.getCurrentPosition());
        telemetry.addData("R Trigger Value" , boost);
        telemetry.addData("Gripper Pos" , robot.gripperB.getCurrentPosition());
        //telemetry.addData("Gyro Z" , gyro.getIntegratedZValue());
        telemetry.update();
    }


    public void setMtrPwr(double Left , double Right)
    {
        robot.right_frnt.setPower(Right);
        robot.right_rear.setPower(Right);
        robot.left_frnt.setPower(Left);
        robot.left_rear.setPower(Left);
    }
}
