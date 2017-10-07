package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
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
 *
 *
 */
public class HardwareLLR
{
    /* Public OpMode members. */
    public DcMotor right_drive = null;
    public DcMotor left_drive = null;
    public Servo colorServ = null;

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
        left_drive = hwMap.dcMotor.get("left");
        right_drive = hwMap.dcMotor.get("right");
        left_drive.setDirection(DcMotor.Direction.REVERSE);
        right_drive.setDirection(DcMotor.Direction.FORWARD);


        // Set all motors to zero power
        left_drive.setPower(0);
        right_drive.setPower(0);

        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Define and initialize ALL installed servos.
        colorServ = hwMap.servo.get("colorServ");
        if (colorServ.getPosition() < ConstUtil.clrServUp) //if less, then move up
        {
            while (colorServ.getPosition() < ConstUtil.clrServUp) //0.5 down to 0
            {
                colorServ.setPosition(colorServ.getPosition() + ConstUtil.clrServRate);
            }
        }
        else if (colorServ.getPosition() > ConstUtil.clrServUp) //if more, then move down
        {
            while (colorServ.getPosition() > ConstUtil.clrServUp)
            {
                colorServ.setPosition(colorServ.getPosition() - ConstUtil.clrServRate);
            }
        }
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

