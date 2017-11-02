package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Nicholas on 2017-10-27.
 */
@Autonomous(name = "RED Right Auto" , group = "Linear OpMode")

public class RedRightAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    public void runOpMode()
    {
        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_drive , robot.left_drive );
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        botMove.changeMode();
        botMove.setDist(-25); //TODO: Change this value later.
        botMove.motorPwrs(0.3);
        botMove.turnGyro(180 , 0.2); //positive turnDegrees turns the bot to the left, negative to the right.
        botMove.turnGyro(ConstUtil.redRightAngR , 0.2);
        botMove.setDist(ConstUtil.redRightDistR);
        botMove.motorPwrs(0.3);
        botMove.turnGyro(-ConstUtil.redRightAngR , 0.1);
        telemetry.addData("Program Place" , "Done");
        telemetry.update();

    }
}
