package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Nicholas on 2017-10-29.
 */

public class RedLeftAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;
    public void runOpMode()
    {
        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_drive , robot.left_drive);
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("progam place" , "hit play");
        telemetry.update();
        waitForStart();
        botMove.changeMode();
        botMove.setDist(ConstUtil.redLeftDistC);
        botMove.motorPwrs(0.5);
        botMove.turnGyro(-90 , 0.2);
    }
}
