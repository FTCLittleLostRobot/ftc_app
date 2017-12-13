package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Const;

/**
 * Created by Nicholas on 2017-09-30.
 */
@Autonomous (name = "Knock Jewel" , group = "Linear Opmode")
@Disabled
public class KnockJewel extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    public ModernRoboticsI2cColorSensor colorSensor = null;
    public ModernRoboticsI2cGyro gyro = null;
    private ElapsedTime runTime = new ElapsedTime();
    private double clrServPos = ConstUtil.clrServUp;

    public void runOpMode()
    {
        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_frnt , robot.right_rear , robot.left_frnt , robot.left_rear, robot.leftlight, this);
        botMove.changeMode();
        colorSensor = (ModernRoboticsI2cColorSensor) hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(false);
        colorSensor.enableLed(true);
        telemetry.addData("Program Place" , "Ready To Run");
        telemetry.update();
        waitForStart();
        while (clrServPos > ConstUtil.clrServDown) //1 down to 0
        {
            clrServPos -= ConstUtil.clrServRate;
            robot.colorServ.setPosition(clrServPos);
            sleep(50);
            idle();
        }

        /*while (colorSensor.red() == 0 && (colorSensor.blue() == 0))
        {
            robot.left_drive.setPower(0.13);
            robot.right_drive.setPower(0.13);
            telemetry.addData("Program Place" , "Moving forward");
        }*/
        robot.left_frnt.setPower(0);
        robot.right_frnt.setPower(0);
        robot.left_rear.setPower(0);
        robot.right_rear.setPower(0);
        sleep(100);
        telemetry.addData("ColorSensor Red" , colorSensor.red());
        telemetry.addData("Color Sensor Blue" , colorSensor.blue());
        telemetry.update();
        sleep(5000);
        telemetry.addData("->" , "Finished Sleeping");
        if (colorSensor.blue() > colorSensor.red()) //If the forward ball is blue, then turn backwards. Otherwise, turn forwards
        {
            /*robot.right_drive.setPower(0.5); // turn backwards (turn left)
            robot.left_drive.setPower(-0.5);
            runTime.reset();
            while (opModeIsActive() && (runTime.seconds() < 0.25))
            {
                telemetry.addData("Program Place" , "turning left");
                telemetry.update();
            }*/
            botMove.setDist(-1.0);
            botMove.motorPwrs(0.2);
            botMove.turnGyro(30 , 0.5);
            telemetry.addData("Program Place" , "stopping");
            telemetry.update();
        }
        else
        {
            /*robot.right_drive.setPower(-0.5); // turn forwards (turn right)
            robot.left_drive.setPower(0.5);
            runTime.reset();
            while (opModeIsActive() && (runTime.seconds() < 0.25))
            {
                telemetry.addData("Program Place" , "turning right");
                telemetry.update();
            }
            robot.right_drive.setPower(0.0);
            robot.left_drive.setPower(0.0);*/
            botMove.turnGyro(-30 , 0.5);
            telemetry.addData("Program Place" , "stopping");
            telemetry.update();
        }
        sleep(500);
        while (clrServPos < ConstUtil.clrServUp) //0.0 up to 1.0
        {
            clrServPos += ConstUtil.clrServRate;
            robot.colorServ.setPosition(clrServPos);
            sleep(50);
            idle();
        }
        telemetry.addData("Program Place" , "DONE");
        telemetry.update();

    }
}