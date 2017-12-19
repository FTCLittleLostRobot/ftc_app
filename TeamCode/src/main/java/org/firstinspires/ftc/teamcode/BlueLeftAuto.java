package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Nicholas on 2017-10-25. TODO:Remember to change the Constants to suit this year's Robot, not MediumBot
 */
@Autonomous(name = "Blue Left Auto" , group = "Linear OpMode")

public class BlueLeftAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    ElapsedTime vufoClock = new ElapsedTime();
    public void runOpMode()
    {
        //robot.teleInit(hardwareMap);
        robot.init(hardwareMap);
        telemetry.addData("Initialize",  "gyro");
        telemetry.update();
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_frnt , robot.right_rear , robot.left_frnt , robot.left_rear, robot.leftlight, this);
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("Initialize",  "finished gyro");
        telemetry.update();
        botMove.changeMode();
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            botMove.changeMode();
            robot.glyphElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //use encoder to set the distance to 1 inch above
            robot.glyphElevator.setPower(1);
            sleep(2000);
            robot.glyphElevator.setPower(0);
            botMove.setDist(14);
            if(!isStopRequested())
            {
                botMove.motorPwrs(0.05);
            }
            if(!isStopRequested())
            {
                botMove.turnGyro(-4 ,0.2); //
            }
            botMove.setDist(ConstUtil.blueLeftDistL - 4);
            if(!isStopRequested())
            {
                botMove.motorPwrs(0.2);
            }
            if(!isStopRequested())
            {
                botMove.turnGyro(-ConstUtil.blueLeftAngL , 0.2);
            }
            robot.glyphElevator.setPower(-1);
            sleep(2000);
            robot.glyphElevator.setPower(0);
            telemetry.addData("Program Place" , "Done");
            telemetry.update();
            stop();
        }

    }

}
