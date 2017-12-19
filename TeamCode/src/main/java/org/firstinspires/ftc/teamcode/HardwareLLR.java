package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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
 * Hub 2:
 *          Motor Port 0: rightfrnt
 *          Motor Port 1: rightrear
 *          Motor Port 2: gripperB
 *
 * Hub 1:
 *          Motor Port 0: leftfrnt
 *          Motor Port 1: leftrear
 *          Motor Port 2: glyphElevator
 *          I2c Port 1: gyro
 *
 *
 */
public class HardwareLLR
{
    /* Public OpMode members. */
    public DcMotor right_frnt = null;
    public DcMotor right_rear = null;
    public DcMotor left_frnt = null;
    public DcMotor left_rear = null;
    public DcMotor leftlight = null;  //julia
    public DcMotor gripperB = null;
    public DcMotor glyphElevator = null;
    public Servo colorServ = null;

    public DigitalChannel touchSensor = null;
    public DigitalChannel limitSwitch = null;
    private int gripInitCheck = 0;
    private boolean limitSwitchCheck = false;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    private ElapsedTime waitTime = new ElapsedTime();

    /* Constructor */
    public HardwareLLR(){

    }

    public void teleInit(HardwareMap ahwMap)
    {
        hwMap = ahwMap;
        // Define and Initialize Motors
        left_frnt = hwMap.dcMotor.get("leftfrnt");
        left_rear = hwMap.dcMotor.get("leftrear");
        right_frnt = hwMap.dcMotor.get("rightfrnt");
        right_rear = hwMap.dcMotor.get("rightrear");
        leftlight = hwMap.dcMotor.get("leftlight");  //julia
        left_frnt.setDirection(DcMotor.Direction.FORWARD);
        left_rear.setDirection(DcMotorSimple.Direction.FORWARD);
        right_frnt.setDirection(DcMotor.Direction.REVERSE);
        right_rear.setDirection(DcMotor.Direction.REVERSE);

        gripperB = hwMap.dcMotor.get("gripperB");
        gripperB.setDirection(DcMotorSimple.Direction.FORWARD);
        glyphElevator = hwMap.dcMotor.get("glyphElevator");
        glyphElevator.setDirection(DcMotorSimple.Direction.REVERSE); //forward moves the elevator down, reverse moves it up

        // Set all motors to zero power
        left_frnt.setPower(0);
        left_rear.setPower(0);
        right_frnt.setPower(0);
        right_rear.setPower(0);
        leftlight.setPower(0);  //julia
        gripperB.setPower(0);
        glyphElevator.setPower(0.0);

        gripperB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right_frnt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_rear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_frnt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_rear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        gripperB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        limitSwitch = hwMap.get(DigitalChannel.class, "limitSwitch"); //Delete this line later

        /*touchSensor = hwMap.get(DigitalChannel.class, "touchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        limitSwitch = hwMap.get(DigitalChannel.class, "limitSwitch");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        limitSwitchCheck = limitSwitch.getState();
        while(limitSwitchCheck == false)
        {
            gripperB.setPower(0.4);
            limitSwitchCheck = limitSwitch.getState();
        }
        gripperB.setPower(0.0);
        gripperB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gripInitCheck = gripperB.getCurrentPosition();
        while(gripInitCheck > ConstUtil.gripperOutCons)
        {
            gripperB.setPower(-0.4);
            gripInitCheck = gripperB.getCurrentPosition();
            //telemetry.addData("GripInitCheck" , gripInitCheck);
            //telemetry.update();
        }
        gripperB.setPower(0);*/

        // Define and initialize ALL installed servos.
        //colorServ = hwMap.servo.get("colorServ");
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        left_frnt = hwMap.dcMotor.get("leftfrnt");
        left_rear = hwMap.dcMotor.get("leftrear");
        leftlight = hwMap.dcMotor.get("leftlight");  //julia
        right_frnt = hwMap.dcMotor.get("rightfrnt");
        right_rear = hwMap.dcMotor.get("rightrear");

        left_frnt.setDirection(DcMotor.Direction.FORWARD);
        left_rear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftlight.setDirection(DcMotorSimple.Direction.FORWARD);  //julia
        right_frnt.setDirection(DcMotor.Direction.REVERSE);
        right_rear.setDirection(DcMotor.Direction.REVERSE);

        gripperB = hwMap.dcMotor.get("gripperB");
        gripperB.setDirection(DcMotorSimple.Direction.FORWARD);
        glyphElevator = hwMap.dcMotor.get("glyphElevator");
        glyphElevator.setDirection(DcMotorSimple.Direction.REVERSE); //forward moves the elevator down, reverse moves it up


        // Set all motors to zero power
        left_frnt.setPower(0);
        left_rear.setPower(0);
        right_frnt.setPower(0);
        right_rear.setPower(0);
        leftlight.setPower(0);  //julia
        gripperB.setPower(0);
        glyphElevator.setPower(0.0);

        gripperB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right_frnt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_rear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_frnt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_rear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        gripperB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define and initialize ALL installed servos.
        //colorServ = hwMap.servo.get("colorServ");

        touchSensor = hwMap.get(DigitalChannel.class, "touchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        limitSwitch = hwMap.get(DigitalChannel.class, "limitSwitch");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        resetLimitSwitch();
        gripInitCheck = gripperB.getCurrentPosition();
        while(gripInitCheck > ConstUtil.gripperOutCons)
        {
            gripperB.setPower(-0.1);
            gripInitCheck = gripperB.getCurrentPosition();
            //telemetry.addData("GripInitCheck" , gripInitCheck);
            //telemetry.update();
        }
        gripperB.setPower(0);
        while(touchSensor.getState())
        {
            //telemetry.addData("Program Place" , "You can press the button now");
            //telemetry.update();
        }
        gripInitCheck = gripperB.getCurrentPosition();
        while(gripInitCheck < ConstUtil.gripperCloseCons)
        {
            gripperB.setPower(0.13);
            gripInitCheck = gripperB.getCurrentPosition();
            //telemetry.addData("GripInitCheck" , gripInitCheck);
            //telemetry.update();
        }
        gripperB.setPower(0);
    }

    public void resetLimitSwitch()
    {
        gripperB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        limitSwitchCheck = limitSwitch.getState();
        while(!limitSwitchCheck)
        {
            gripperB.setPower(0.3);
            limitSwitchCheck = limitSwitch.getState();
        }
        gripperB.setPower(0.0);
        gripperB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gripperB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
}

    /**
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

