package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicholas on 2017-10-14.
 */
@TeleOp(name = "Glyph Elevator Test" , group = "Iterative OpMode")
@Disabled
public class GlyphElevatorTest extends OpMode
{
    HardwareLLR robot = new HardwareLLR();
    private int elevatorPos = 0;
    private boolean elevatorOver = false;

    public void init()
    {
        robot.teleInit(hardwareMap);
        robot.glyphElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("->" , "Dpad Up moves the elevator up, Dpad Down moves it down.");
        telemetry.update();
    }

    public void start()
    {
        robot.glyphElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.glyphElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop()
    {
        elevatorPos = robot.glyphElevator.getCurrentPosition();
        if(gamepad2.y)
        {
            elevatorOver = true;
            robot.glyphElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else
        {

        }
        if (gamepad2.dpad_up)
        {
            if(elevatorOver)
            {
                robot.glyphElevator.setPower(1);
            }
            else
            {
                if (elevatorPos >= ConstUtil.elevatorUpCons)
                {
                    robot.glyphElevator.setPower(0);
                }
                else
                {
                    robot.glyphElevator.setPower(1);
                }
            }
        }
        else if(gamepad2.dpad_down)
        {
            if(elevatorOver)
            {
                robot.glyphElevator.setPower(-1);
            }
            else
            {
                if (elevatorPos <= ConstUtil.elevatorDownCons)
                {
                    robot.glyphElevator.setPower(0);
                }
                else
                {
                    robot.glyphElevator.setPower(-1);
                }
            }
        }
        else
        {
            robot.glyphElevator.setPower(0.0);
        }
        telemetry.addData("Elevator Pos" , robot.glyphElevator.getCurrentPosition());
        telemetry.update();
    }
}
