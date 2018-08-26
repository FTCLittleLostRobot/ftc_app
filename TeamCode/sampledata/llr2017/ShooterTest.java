package org.firstinspires.ftc.teamcode.llr2017;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Nicholas on 2017-07-20.
 */

@TeleOp(name="ShooterTest", group="Iterative Opmode")
@Disabled
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
        //robot.shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //remove comment later
        lastTime = timestamp;
        lstTicks = crntTicks;
    }

    public void loop()
    {
        timestamp = getRuntime();
        //crntTicks = robot.shooter.getCurrentPosition();  //remove comment later
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
                    //robot.collect.setPower(-0.5);  //remove comment later
                }
                    else
                    {
                        //robot.collect.setPower(1);  //remove comment later
                    }
            }
            else
            {
                //robot.collect.setPower(0);  //remove comment later
            }

            if (gamepad2.y = (rampUpDone = true) && (rBump = true))
            {
                //robot.ballGate.setPosition(0.6);  //remove comment later
            }
            else
            {
                //robot.ballGate.setPosition(0.9);  //remove comment later
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
        //robot.shooter.setPower(shootCounter);  //remove comment later
        //telemetry.addData("Shooter Power" , robot.shooter.getPower());  //remove comment later
        telemetry.addData("Ticks" , tksRPM);
        telemetry.update();
        lstTicks = crntTicks;
        lastTime = timestamp;
    }
}