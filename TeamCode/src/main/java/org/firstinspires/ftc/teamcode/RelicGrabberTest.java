package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-10-07.
 */
@TeleOp(name = "Relic Grabber Test" , group = "Iterative OpMode")
@Disabled
public class RelicGrabberTest extends OpMode
{
    HardwareLLR robot = new HardwareLLR();
    private CRServo rGrab = null;

    public void init()
    {
        robot.init(hardwareMap);
        rGrab = (CRServo)hardwareMap.crservo.get("relicGrabber");
        rGrab.setDirection(DcMotorSimple.Direction.FORWARD); //might need to change this later
        telemetry.addData("->" , "ready to run");
    }

    public void loop()
    {
        if(gamepad2.dpad_up)
        {
            rGrab.setPower(1);
        }
        else if(gamepad2.dpad_down)
        {
            rGrab.setPower(-1);
        }
        else
        {
            rGrab.setPower(0);
        }
        telemetry.addData("Relic Grabber Power" , rGrab.getPower());
    }
}
