package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Nicholas on 2017-10-29.
 */
@Autonomous(name = "BLUE RIGHT Auto" , group = "Linear OpMode")

public class BlueRightAuto extends LinearOpMode
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
        botMove.setDist(ConstUtil.blueRightDistR);
        botMove.motorPwrs(0.3);
        botMove.turnGyro(90, 0.2);
        botMove.setDist(ConstUtil.after90TurnDist);
        botMove.motorPwrs(0.3);
        telemetry.addData("Program Place" , "Done");
        telemetry.update();
    }
}
