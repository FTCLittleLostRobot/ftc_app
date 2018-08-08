package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by julia/Team on 12/8/2017.
 * This Linear Opmode turns on the light for the VuMark gradually from 0 to full power in a loop.  The light is
 * plugged into Motor 3, set mode to FORWARD.  Should turn off when VuMark is done.
 */
@Autonomous (name = "Light On Auto", group = "Linear OpMode")
@Disabled
public class LightAuto extends LinearOpMode
{
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;


    public void runOpMode()
    {

        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_frnt , robot.right_rear , robot.left_frnt , robot.left_rear, robot.leftlight, this);
        telemetry.addData("Program Place", "Ready to Run");
        telemetry.update();
        waitForStart();
        for (int i = 0; i < 5; i++ ){
            botMove.lLight.setPower(0.2*i);
            sleep(2000);
            telemetry.addData("Lights at ", i);
            telemetry.update();
        }
        botMove.lLight.setPower(0);  //turn off lights at the end of opMode.
        telemetry.addData("Lights ", "off");
        telemetry.update();
    }
}
