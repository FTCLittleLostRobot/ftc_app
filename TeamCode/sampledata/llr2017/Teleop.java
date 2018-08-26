package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-05.
 */
@TeleOp(name = "Teleop" , group = "Iterative Opmode")
@Disabled
public class Teleop extends OpMode
{

    double rStk;
    double lStk;
    double boost;
    double lTrig;
    double rMtrPwr;
    double lMtrPwr;

    private boolean gripPower = false;
    private int elevatorPos = 0;
    private boolean elevatorOver = false;
    private int gripperPos = 0;
    private boolean gripped = false;
    private boolean autoRaiseDone = false;
    private boolean resetGripper = false;

    HardwareLLR robot = new HardwareLLR();

    public void init()
    {
     robot.teleInit(hardwareMap);
     /*gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
     gyro.calibrate();
     gyro.resetZAxisIntegrator();*/
     robot.glyphElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
     telemetry.update();
     telemetry.addData("Status" , "Ready");
     telemetry.update();
    }

    public void start()
    {
        robot.glyphElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        elevatorPos = robot.glyphElevator.getCurrentPosition();
        gripperPos = robot.gripperB.getCurrentPosition();
        rStk = gamepad1.right_stick_y*-1;
        lStk = gamepad1.left_stick_y*-1;
        boost = gamepad1.right_trigger;
        lTrig = gamepad1.left_trigger;

        if(gamepad2.y)
        {
            elevatorOver = true;
            robot.glyphElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        if(gamepad2.x)
        {
            if(!resetGripper)
            {
                gripPower = true;
                robot.gripperB.setPower(0.01);
            }
        }
        else
        {
            gripPower = false;
        }

        if(gamepad2.b)
        {
            resetGripper = true;
        }

        if(resetGripper)
        {
            if (!robot.limitSwitch.getState())
            {
                robot.gripperB.setPower(0.3);
            }
            else
            {
                robot.gripperB.setPower(0);
                robot.gripperB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                resetGripper = false;
            }
        }

        if (gamepad2.right_bumper)
        {
            if(!gripPower)
            {
                if(!resetGripper)
                {
                    if(gripperPos >= ConstUtil.gripperOutCons)
                    {
                        robot.gripperB.setPower(-0.20);
                        gripped = false;
                    }
                    else
                    {
                        robot.gripperB.setPower(0.0);
                    }
                }
            }

        }
        else if(gamepad2.left_bumper)
        {
            if(!gripPower&&!resetGripper)
            {

                if(gripperPos <= ConstUtil.gripperCloseCons)
                {
                    robot.gripperB.setPower(0.20);
                }
                else
                {
                    robot.gripperB.setPower(0.001);
                    gripped = true;
                }
            }
        }
        else  //  right bumper and left bumper is not pressed
        {
            if(!gripPower)
            {
                if(!resetGripper)
                {
                    robot.gripperB.setPower(0.0);
                }
            }
        }

        if (gamepad2.dpad_up)
        {
            gripped = false;
            if(elevatorOver)
            {
                robot.glyphElevator.setPower(1);
            }
            else
            {
                if(autoRaiseDone)
                {
                    if (elevatorPos >= ConstUtil.elevatorUpCons)
                    {
                        robot.glyphElevator.setPower(0);
                    }
                    else
                    {
                        robot.glyphElevator.setPower(1);
                    }
                }
            }
        }
        else if(gamepad2.dpad_down)
        {
            gripped = false;
            if(elevatorOver)
            {
                robot.glyphElevator.setPower(-1);
            }
            else
            {
                if (elevatorPos <= ConstUtil.elevatorDownCons)
                {
                    robot.glyphElevator.setPower(0);
                }
                else
                {
                    robot.glyphElevator.setPower(-1);
                }
            }
        }
        else  // not dpad_up or dpad_down is pressed
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
        if(elevatorOver)
        {
            gripped = false;
        }
        if(gripped)
        {
            if(elevatorPos >= ConstUtil.elevatorGripUp)
            {
                robot.glyphElevator.setPower(0);
                autoRaiseDone = true;
            }
            else
            {
                robot.glyphElevator.setPower(1);
                autoRaiseDone = false;
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
        telemetry.addData("gripped" , gripped);
        telemetry.addData("autoRaiseDone" , autoRaiseDone);
        telemetry.addData("Elevator Position" , robot.glyphElevator.getCurrentPosition());
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