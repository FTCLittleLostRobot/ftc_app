package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
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
    ModernRoboticsI2cGyro gyro = null;

    HardwareLLR robot = new HardwareLLR();


    public void init()
    {
      robot.init(hardwareMap);
      gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
      gyro.calibrate();
      gyro.resetZAxisIntegrator();
      telemetry.addData("Status" , "Ready");
      telemetry.update();
    }

    public void start()
    {
        robot.right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER );
        robot.left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
    }

    public void loop()
    {
        rStk = gamepad1.right_stick_y*-1;
        lStk = gamepad1.left_stick_y*-1;
        boost = gamepad1.right_trigger;
        lTrig = gamepad1.left_trigger;
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
        telemetry.addData("Right Motor Power" , robot.right_drive.getPower());
        telemetry.addData("Right Motor POSITION" , robot.right_drive.getCurrentPosition());
        telemetry.addData("Left Motor Power" , robot.left_drive.getPower());
        telemetry.addData("Left Motor POSITION" , robot.left_drive.getCurrentPosition());
        telemetry.addData("R Trigger Value" , boost);
        telemetry.addData("Gyro Z" , gyro.getIntegratedZValue());
        telemetry.update();
    }

    public void setMtrPwr(double Left , double Right)
    {
        robot.right_drive.setPower(Right);
        robot.left_drive.setPower(Left);
    }
}
