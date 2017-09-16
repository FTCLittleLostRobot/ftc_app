package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is our BabyBot. If we remove the comments, this class will be for our Little Lost Robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  left  drive motor:        "left"
 * Motor channel:  right drive motor:        "right"
 * more servos coming soon
 * Todo: Because we are using the little robot, remove the comments for the rest of the servos and motors later.
 */
public class HardwareLLR
{
    /* Public OpMode members. */
    public DcMotor right = null;
    public DcMotor left = null;
   // public DcMotor shooter = null;   //remove comment later
    //public DcMotor  collect = null;  //remove comment later
    //public Servo wigWag    = null;  //remove comment later
    //public Servo ballGate = null;  //remove comment later

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareLLR(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        left = hwMap.dcMotor.get("left");
        right = hwMap.dcMotor.get("right");
       // shooter = hwMap.dcMotor.get("shooter");  //remove comment later
        //collect = hwMap.dcMotor.get("collect");  //remove comment later
        //ballGate = hwMap.servo.get("ballGate");  //remove comment later
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        //shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        //collect.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all motors to zero power
        left.setPower(0);
        right.setPower(0);
        //ballGate.setPosition(1);  //remove comment later

        // Set all motors to run without encoders.  //remove comment later
        // May want to use RUN_USING_ENCODERS if encoders are installed.  //remove comment later
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);  //remove comment later

        // Define and initialize ALL installed servos.
        //leftClaw = hwMap.servo.get("left_hand"); //For future reference. Need to add more later
        //wigWag = hwMap.servo.get("wigWag");  //remove comment later
        //wigWag.setPosition(ConstUtil.wigWagRight);  //remove comment later

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
        {
            try
            {
                Thread.sleep(remaining);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

