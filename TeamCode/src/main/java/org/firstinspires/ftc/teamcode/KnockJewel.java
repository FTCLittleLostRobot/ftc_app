package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nicholas on 2017-09-30. Right now, I'm running the motors by time.
 */
@Autonomous (name = "Knock Jewel" , group = "Linear Opmode")

public class KnockJewel extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    public ModernRoboticsI2cColorSensor colorSensor = null;
    public ModernRoboticsI2cGyro gyro = null;
    private ElapsedTime runTime = new ElapsedTime();

    public void runOpMode()
    {
        robot.init(hardwareMap);
        robot.left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        colorSensor = (ModernRoboticsI2cColorSensor) hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(false);
        colorSensor.enableLed(true);
        telemetry.addData("Program Place" , "Ready To Run");
        telemetry.update();
        waitForStart();
        while (robot.colorServ.getPosition() > ConstUtil.clrServDown) //0.5 down to 0
        {
            robot.colorServ.setPosition(robot.colorServ.getPosition() - ConstUtil.clrServRate );
        }

        /*while (colorSensor.red() == 0 && (colorSensor.blue() == 0))
        {
            robot.left_drive.setPower(0.13);
            robot.right_drive.setPower(0.13);
            telemetry.addData("Program Place" , "Moving forward");
        }*/
        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);
        telemetry.addData("ColorSensor Red" , colorSensor.red());
        telemetry.addData("Color Sensor Blue" , colorSensor.blue());
        telemetry.update();
        sleep(5000);
        telemetry.addData("->" , "Finished Sleeping");
        if (colorSensor.blue() > colorSensor.red()) //If the forward ball is blue, then turn backwards. Otherwise, turn forwards
        {
            robot.right_drive.setPower(0.5); // turn backwards (turn left)
            robot.left_drive.setPower(-0.5);
            runTime.reset();
            while (opModeIsActive() && (runTime.seconds() < 0.25))
            {
                telemetry.addData("Program Place" , "turning left");
                telemetry.update();
            }
            robot.right_drive.setPower(0.0);
            robot.left_drive.setPower(0.0);
            telemetry.addData("Program Place" , "stopping");
            telemetry.update();
        }
        else
        {
            robot.right_drive.setPower(-0.5); // turn forwards (turn right)
            robot.left_drive.setPower(0.5);
            runTime.reset();
            while (opModeIsActive() && (runTime.seconds() < 0.25))
            {
                telemetry.addData("Program Place" , "turning right");
                telemetry.update();
            }
            robot.right_drive.setPower(0.0);
            robot.left_drive.setPower(0.0);
            telemetry.addData("Program Place" , "stopping");
            telemetry.update();
        }
        sleep(500);
        while (robot.colorServ.getPosition() < ConstUtil.clrServUp) //0  to 0.5
        {
            robot.colorServ.setPosition(robot.colorServ.getPosition() + ConstUtil.clrServRate);
        }
        telemetry.addData("Program Place" , "DONE");
        telemetry.update();

    }
}