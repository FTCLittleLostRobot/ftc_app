package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRColor;
import org.firstinspires.ftc.robotcore.external.Const;

/**
 * Created by Nicholas on 2017-09-22.
 */

@TeleOp(name = "Servo Color Test" , group = "Iterative OpMode")

public class ServoColorTest extends OpMode
{
    private boolean up = true;
    private boolean lstDpdDOWN = false;
    private boolean crntDpdDOWN = false;
    private boolean lstDpdUP = false;
    private boolean crntDpdUP = false;
    private ModernRoboticsI2cColorSensor colorSensor = null;

    HardwareLLR robot = new HardwareLLR();

    public void init()
    {
        robot.init(hardwareMap);
        colorSensor = (ModernRoboticsI2cColorSensor) hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(true);
        telemetry.addData("->" , "Through Init");
    }

    public void loop()
    {
        telemetry.addData("Servo Pos Before Pad" , robot.colorServ.getPosition());
        if (gamepad2.dpad_down)
        {
            lstDpdDOWN = crntDpdDOWN;
            if (!crntDpdDOWN)
            {
                crntDpdDOWN = true;
                up = false;
            }
            else
                {
                    crntDpdDOWN = false;
                    up = false;
                }
        }

        if (gamepad2.dpad_up)
        {
            lstDpdUP = crntDpdUP;
            if (!crntDpdUP)
            {
                crntDpdUP = true;
                up = true;
            }
            else
                {
                    crntDpdUP = false;
                    up = true;
                }
        }

        if (up)
        {
            while (robot.colorServ.getPosition() < ConstUtil.clrServUp)
            {
                robot.colorServ.setPosition(robot.colorServ.getPosition() + ConstUtil.clrServRate);
            }
        }
        else
        {
            while (robot.colorServ.getPosition() > ConstUtil.clrServDown)
            {
                robot.colorServ.setPosition(robot.colorServ.getPosition() - ConstUtil.clrServRate);
            }
        }

        telemetry.addData("Color Red" , colorSensor.red());
        telemetry.addData("Color Blue" , colorSensor.blue());
        telemetry.addData("Color Green" , colorSensor.green());
    }
}
