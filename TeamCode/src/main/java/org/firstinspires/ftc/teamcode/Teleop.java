package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Nicholas on 2017-08-05.
 */
@TeleOp(name = "Teleop" , group = "Iterative Opmode")
public class Teleop extends OpMode
{

    double spdMulty = 0.25;
    double rStk;
    double lStk;
    double boost;
    double deadZone = 0.15;
    double rMtrPwr;
    double lMtrPwr;

    HardwareLLR robot = new HardwareLLR();


    public void init()
    {
      robot.init(hardwareMap);
      telemetry.addData("Status" , "Ready");
      telemetry.update();
    }

    public void start()
    {
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        rStk = gamepad1.right_stick_y;
        lStk = gamepad1.left_stick_y;
        boost = gamepad1.right_trigger;
        if(Math.abs(rStk) < deadZone)
            rStk = 0.0;

        if(Math.abs(lStk) < deadZone)
            lStk = 0.0;


        if (boost == ConstUtil.boostCons)
        {
            rMtrPwr = rStk;
            lMtrPwr = lStk;
        }
        else
        {
            rMtrPwr = rStk * spdMulty;
            lMtrPwr = lStk * spdMulty;
        }
        setMtrPwr(lMtrPwr , rMtrPwr);
        telemetry.addData("Right Motor Power" , robot.right.getPower());
        telemetry.addData("Right Motor POSITION" , robot.right.getCurrentPosition());
        telemetry.addData("Left Motor Power" , robot.left.getPower());
        telemetry.addData("Left Motor POSITION" , robot.left.getCurrentPosition());
        telemetry.update();
    }

    public void setMtrPwr(double Left , double Right)
    {
        robot.right.setPower(Right);
        robot.left.setPower(Left);
    }
}
