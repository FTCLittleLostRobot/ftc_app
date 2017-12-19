package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Nicholas on 2017-10-29.
 */
@Autonomous(name = "RED LEFT Auto" , group = "Linear OpMode")

public class RedLeftAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    public void runOpMode()
    {
        //robot.teleInit(hardwareMap);
        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_frnt , robot.right_rear , robot.left_frnt , robot.left_rear, robot.leftlight, this);
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            robot.glyphElevator.setPower(1);
            sleep(2000);
            robot.glyphElevator.setPower(0);
            botMove.changeMode();
            botMove.setDist(ConstUtil.offRampDist10_27);
            if(!isStopRequested())
            {
                botMove.motorPwrs(0.05);
            }
            if(!isStopRequested())
            {
                botMove.turnGyro(-90, 0.2);
            }
            robot.glyphElevator.setPower(-1);
            sleep(2000);
            robot.glyphElevator.setPower(0);
            stop();
        }
    }
}
