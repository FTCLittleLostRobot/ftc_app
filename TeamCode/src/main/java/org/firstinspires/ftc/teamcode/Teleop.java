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

    double rStk;
    double lStk;
    double boost;
    double lTrig;
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
        robot.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER );
        robot.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        rStk = gamepad1.right_stick_y;
        lStk = gamepad1.left_stick_y;
        boost = gamepad1.right_trigger;
        lTrig = gamepad1.left_trigger;
        telemetry.addData("R trigger val after read" , boost);
        telemetry.addData("R stk after read" , rStk);
        telemetry.addData("L stk after read" , lStk);
        if (lTrig == 1.0)
        {
            rMtrPwr = 0.0;
            lMtrPwr = 0.0;
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
        telemetry.addData("rMtrPwr Value" , rMtrPwr);
        telemetry.addData("lMtrPwr Value" , lMtrPwr);
        setMtrPwr(lMtrPwr , rMtrPwr);
        telemetry.addData("Right Motor Power" , robot.right.getPower());
        //telemetry.addData("Right Motor POSITION" , robot.right.getCurrentPosition());
        telemetry.addData("Left Motor Power" , robot.left.getPower());
        //telemetry.addData("Left Motor POSITION" , robot.left.getCurrentPosition());
        telemetry.addData("R Trigger Value" , boost);
        telemetry.update();
    }

    public void setMtrPwr(double Left , double Right)
    {
        robot.right.setPower(Right);
        robot.left.setPower(Left);
    }
}
