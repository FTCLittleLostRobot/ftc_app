package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Nicholas on 2017-10-29.
 */
@Autonomous(name = "BLUE RIGHT Auto Test for stop()" , group = "Linear OpMode")
@Disabled
public class BlueRightAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    public void runOpMode() throws InterruptedException
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            //robot.teleInit(hardwareMap);
            robot.init(hardwareMap);
            gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
            BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro, robot.right_frnt, robot.right_rear, robot.left_frnt, robot.left_rear, robot.leftlight, this);
            gyro.calibrate();
            gyro.resetZAxisIntegrator();
            telemetry.addData("Program Place", "Ready to Run");
            telemetry.update();
            waitForStart();

            while (opModeIsActive() && !isStopRequested())
            {
                robot.glyphElevator.setPower(1);
                sleep(2000);
                robot.glyphElevator.setPower(0);
                botMove.changeMode();
                botMove.setDist(ConstUtil.offRampDist10_27);
                if (!isStopRequested())
                {
                    botMove.motorPwrs(0.05);
                }

                if (!isStopRequested())
                {
                    botMove.turnGyro(90, 0.2);
                }
                botMove.setDist(ConstUtil.after90TurnDist - 4);
                if (!isStopRequested())
                {
                    botMove.motorPwrs(0.2);
                }
                robot.glyphElevator.setPower(-1);
                sleep(2000);
                robot.glyphElevator.setPower(0);
                stop();
                telemetry.addData("Program Place", "Done");
                telemetry.update();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(pw);
            telemetry.addLine("Crashed at:" + pw.toString());
            telemetry.update();

        }
    }
}
