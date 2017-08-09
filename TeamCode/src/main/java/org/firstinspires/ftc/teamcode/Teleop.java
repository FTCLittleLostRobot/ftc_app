package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Nicholas on 2017-08-05.
 */
@TeleOp(name = "Teleop" , group = "Iterative Opmode")
public class Teleop extends OpMode
{
    private DcMotor right;
    private DcMotor left;
    double spdMulty = 0.25;
    double rStk;
    double lStk;
    double boost;
    double deadZone = 0.15;
    double rMtrPwr;
    double lMtrPwr;

    public void init()
    {
      right.setDirection(DcMotorSimple.Direction.REVERSE);
      left.setDirection(DcMotorSimple.Direction.FORWARD);
      right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      setMtrPwr(0,0);
      telemetry.addData("Status" , "Ready");
      telemetry.update();
    }

    public void start()
    {
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        rStk = gamepad1.right_stick_y;
        lStk = gamepad1.left_stick_y;
        boost = gamepad1.right_trigger;
        if(Math.abs(rStk) < deadZone)
            rStk = 0;

        if(Math.abs(lStk) < deadZone)
            lStk = 0;

        if (boost == 1)
        {
            rMtrPwr = rStk;
            lMtrPwr = lStk;
        }
        else
        {
            rMtrPwr = rStk * spdMulty;
            lMtrPwr = lStk * spdMulty;
        }
        setMtrPwr(-lMtrPwr , -rMtrPwr);
        telemetry.addData("Right Motor Power" , right.getPower());
        telemetry.addData("Right Motor POSITION" , right.getCurrentPosition());
        telemetry.addData("Left Motor Power" , left.getPower());
        telemetry.addData("Left Motor POSITION" , left.getCurrentPosition());
        telemetry.update();
    }

    public void setMtrPwr(double Left , double Right)
    {
        right.setPower(Right);
        left.setPower(Left);
    }
}
