package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nicholas on 2017-07-20.
 */

@TeleOp(name="ShooterTest", group="Iterative Opmode")
public class ShooterTest extends OpMode
{

    boolean aBtnCrnt = false;
    boolean rampUpDone = false;
    double timestamp = 0;
    double lastTime = 0;
    double crntTicks = 0;
    double shootCounter = 0; //This variable is also the power to the shooter motor.
    double tgtPower = 0.21;
    boolean rBump = false;
    double lstTicks = 0;
    double timeGonBi = 0;
    double tksGonBi = 0;
    double tksRPM = 0;

    HardwareLLR robot = new HardwareLLR();

    public void init()
    {
        robot.init(hardwareMap);
    }

    public void start()
    {
        robot.shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lastTime = timestamp;
        lstTicks = crntTicks;
    }

    public void loop()
    {
        timestamp = getRuntime();
        crntTicks = robot.shooter.getCurrentPosition();
        timeGonBi = timestamp - lastTime;
        tksGonBi = crntTicks - lstTicks;
        tksRPM = tksGonBi / timeGonBi;
            if (gamepad2.a = true)
            {
                while (gamepad2.a = true)
                {

                }
            aBtnCrnt = !aBtnCrnt;
            }

            if (gamepad2.right_bumper = true)
            {
                while (gamepad2.right_bumper = true)
                {

                }
            rBump = !rBump;
            }

            if (rBump = true)
            {
                if (gamepad2.left_bumper = true)
                {
                    robot.collect.setPower(-0.5);
                }
                    else
                    {
                        robot.collect.setPower(1);
                    }
            }
            else
            {
                robot.collect.setPower(0);
            }

            if (gamepad2.y = (rampUpDone = true) && (rBump = true))
            {
                robot.ballGate.setPosition(0.6);
            }
            else
            {
                robot.ballGate.setPosition(0.9);
            }

            if (aBtnCrnt = true)
            {
                if (shootCounter < tgtPower)
                {
                    shootCounter = shootCounter + ConstUtil.rampUpRate;
                }
                else
                {
                    shootCounter = tgtPower;
                    rampUpDone = true;
                }
            }
            else
            {
                shootCounter = 0;
            }
        robot.shooter.setPower(shootCounter);
        telemetry.addData("Shooter Power" , robot.shooter.getPower());
        telemetry.addData("Ticks" , tksRPM);
        telemetry.update();
        lstTicks = crntTicks;
        lastTime = timestamp;
    }
}