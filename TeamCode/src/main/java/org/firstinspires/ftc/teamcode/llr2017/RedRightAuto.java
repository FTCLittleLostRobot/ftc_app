package org.firstinspires.ftc.teamcode.llr2017;

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
        /*robot.glyphElevator.setPower(1);
        sleep(2000);
        robot.glyphElevator.setPower(0);*/
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
            //positive turnDegrees turns the bot to the left, negative to the right.
            botMove.setDist(14);
            if(!isStopRequested())
            {
                botMove.motorPwrs(0.05);
            }
            if(!isStopRequested())
            {
                botMove.turnGyro(4 , 0.2);
            }
            botMove.setDist(ConstUtil.blueLeftDistL - 4);
            if(!isStopRequested())
            {
                botMove.motorPwrs(0.2);
            }
            //botMove.turnGyro(-ConstUtil.blueLeftAngL , 0.3);
            robot.glyphElevator.setPower(-1);
            sleep(2000);
            robot.glyphElevator.setPower(0);
            stop();
            telemetry.addData("Program Place" , "Done");
            telemetry.update();
        }
    }
}
