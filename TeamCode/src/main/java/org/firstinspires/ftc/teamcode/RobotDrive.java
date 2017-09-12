package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-08-04. This is similar to teleop but with using threads
 */

@TeleOp(name = "Threads", group ="Iterative OpMode" )

public class RobotDrive extends OpMode
{

    HardwareLLR robot = new HardwareLLR();

    DriveThrd t1;
    DriveThrd t2;

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
        t1 = new DriveThrd ( DriveThrd.CONST_LEFT , gamepad1 , robot.left); //make different threads
        t2 = new DriveThrd( DriveThrd.CONST_RIGHT , gamepad1 , robot.right);
        Thread thrd1 = new Thread(t1);
        Thread thrd2 = new Thread(t2);
        thrd1.start();
        thrd2.start();
    }

    public void loop()
    {
        telemetry.addData("Right Pos" , robot.right.getCurrentPosition());
        telemetry.addData("Right Power" , robot.right.getPower());
        telemetry.addData("Left Pos" , robot.left.getCurrentPosition());
        telemetry.addData("Left Power" , robot.left.getPower());
    }

    public void stop()
    {
        t1.stopThread(true); //In the thread, the loop won't work because stopthrd is true
        t2.stopThread(true);
    }

}



