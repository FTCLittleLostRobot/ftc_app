package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Nicholas on 2017-08-04. This is similar to teleop but with using threads
 */

@TeleOp(name = "Threads", group ="Iterative OpMode" )
@Disabled
public class RobotDrive extends OpMode
{
    private DcMotor right = null;
    private DcMotor left = null;;

    DriveThrd t1;
    DriveThrd t2;

    public void init()
    {
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Status" , "Ready");
        telemetry.update();
    }

    public void start()
    {
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
        t1 = new DriveThrd ( DriveThrd.CONST_LEFT , gamepad1 ); //make different threads
        t2 = new DriveThrd( DriveThrd.CONST_RIGHT , gamepad1);
        Thread thrd1 = new Thread(t1);
        Thread thrd2 = new Thread(t2);
        thrd1.start();
        thrd2.start();
    }

    public void loop()
    {

    }

    public void stop()
    {
        t1.stopThread(true); //In the thread, the loop won't work because stopthrd is true
        t2.stopThread(true);
    }

}



