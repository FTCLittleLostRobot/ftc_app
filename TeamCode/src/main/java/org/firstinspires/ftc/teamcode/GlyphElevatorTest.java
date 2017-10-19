package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Nicholas on 2017-10-14.
 */
@TeleOp(name = "Glyph Elevator Test" , group = "Iterative OpMode")

public class GlyphElevatorTest extends OpMode
{
    HardwareLLR robot = new HardwareLLR();
    DcMotor glyphElevator = null;

    public void init()
    {
        robot.init(hardwareMap);
        glyphElevator = hardwareMap.dcMotor.get("glyphElevator");
        glyphElevator.setDirection(DcMotorSimple.Direction.FORWARD); //forward moves the elevator up, reverse moves it down
        glyphElevator.setPower(0.0);
        telemetry.addData("->" , "Dpad Up moves the elevator up, Dpad Down moves it down.");
        telemetry.update();
    }

    public void loop()
    {
        if (gamepad2.dpad_up)
        {
            glyphElevator.setPower(1.0);
        }
        else if(gamepad2.dpad_down)
        {
            glyphElevator.setPower(-1.0);
        }
        else
        {
            glyphElevator.setPower(0.0);
        }
    }
}
