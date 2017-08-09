package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Nicholas on 2017-08-08.
 */

public class DriveThrd implements Runnable
{
    public static final int CONST_LEFT = 1; //constants
    public static final int CONST_RIGHT = 2; //constants

    private DcMotor right;//motors
    private DcMotor left;//another motor
    private Gamepad pad; //gamepad
    private int side; //depending on CONST_LEFT and CONST_RIGHT.
    private boolean stopThrd; //The boolean to stop the thread

    public DriveThrd(int side , Gamepad pad) //constructor
    {
        this.side = side;
        this.pad = pad;
        stopThrd = false;
    }

    public void stopThread(boolean flag) //method to stop the thread
    {
        this.stopThrd = flag;
    }

    public void run()
    {
        double stkVal = 0.0; //variable for reading joystick values
        double boost = 0.0; //variable for reading trigger button variables

        while(!stopThrd)
        {
            boost = pad.right_trigger; //get the trigger value
            switch (side)
            {
                case CONST_LEFT: //this runs if this thread is for the left side
                    // read gamepad left joystick
                    stkVal = pad.left_stick_y;
                    if(checkDZone(stkVal)) //does the stick value meet the deadzone requirements?
                    {
                        stkVal = 0.0;
                    }
                    left.setPower(boostFactor(boost , stkVal)); //send power to the motors

                    break;

                case CONST_RIGHT: //this runs if this thread is for the right side.
                    // read the gamepad right joystick
                    stkVal = pad.right_stick_y;
                    if(checkDZone(stkVal))
                    {
                        stkVal = 0.0;
                    }
                    right.setPower(boostFactor(boost , stkVal));
                    break;

                default:
                    break;
            }  // switch
        }  // while

        // terminate the thread cause stopThrd == true

    }  // run

    private boolean checkDZone(double checkVal) //this checks the stick value against the deadzome
    {
        boolean returnVal = false;
        if(Math.abs(checkVal) < ConstUtil.deadZone)
        {
            returnVal = true;
        }
        return returnVal;
    }

    private double boostFactor(double boost , double stkVal) //this checks the boost button
    {
        double ret = stkVal*-1; //this is negative because if you push the stick up, the value is negative.

        if(boost < 1.0)
        {
            ret = ret*ConstUtil.spdMulty;
        }
        return ret;
    }
}
