package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-08-04. This is similar to teleop but with using threads
 */

@TeleOp(name = "Threads", group ="Iterative OpMode" )
@Disabled
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
        robot.right_frnt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.left_frnt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status" , "Go Time!");
        telemetry.update();
        t1 = new DriveThrd ( DriveThrd.CONST_LEFT , gamepad1 , robot.left_frnt); //make different threads
        t2 = new DriveThrd( DriveThrd.CONST_RIGHT , gamepad1 , robot.right_frnt);
        Thread thrd1 = new Thread(t1);
        Thread thrd2 = new Thread(t2);
        thrd1.start();
        thrd2.start();
    }

    public void loop()
    {
        telemetry.addData("Right Rear Pos" , robot.right_rear.getCurrentPosition());
        telemetry.addData("Right Frnt Power" , robot.right_frnt.getPower());
        telemetry.addData("Right Rear Power" , robot.right_rear.getPower());
        telemetry.addData("Left Rear Pos" , robot.left_rear.getCurrentPosition());
        telemetry.addData("Left Frnt Power" , robot.left_frnt.getPower());
        telemetry.addData("Left Rear Power" , robot.left_rear.getPower());
    }

    public void stop()
    {
        t1.stopThread(true); //In the thread, the loop won't work because stopthrd is true
        t2.stopThread(true);
    }

}



