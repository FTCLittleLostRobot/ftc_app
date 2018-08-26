package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nicholas on 2017-10-11.
 */
@Autonomous (name = "Color Servo Fix" , group = "Linear Opmode")
@Disabled
public class ColorServoFix extends LinearOpMode
{
    Servo colorServ = null;
    private double servoPos = 1.0;

    public void runOpMode()
    {
        colorServ = hardwareMap.servo.get("colorServ");
        servoPos = 1; //was colorServ.getPosition()
        telemetry.addData("servoPos" , servoPos);
        telemetry.addData("clrServUp", 1); //second parameter was ConstUtil.clorServUp
        telemetry.update();
        waitForStart();
        while (servoPos > 0.0)
        {
            servoPos -= 0.01;
            colorServ.setPosition(servoPos);
            sleep(50);
            idle();
        }
        sleep(3000);
        while (servoPos < 1.0)
        {
            servoPos += 0.01;
            colorServ.setPosition(servoPos);
            sleep(50);
            idle();
        }
        telemetry.addData("Program Place" , "Done");
        telemetry.update();
    }
}
