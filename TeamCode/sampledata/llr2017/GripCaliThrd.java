package org.firstinspires.ftc.teamcode.llr2017;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by N on 2017-11-30.
 */

public class GripCaliThrd implements Runnable
{
    private boolean bBtn;
    private boolean stopThrd;
    private Gamepad pad2;
    private DcMotor gripperB;
    private DigitalChannel limitSwitch;
    boolean returnVal = false;

    public GripCaliThrd(Gamepad pad , DcMotor gripper , DigitalChannel limitSwitch) //constructor
    {
        pad2 = pad;
        gripperB = gripper;
        this.bBtn = bBtn;
        stopThrd = false;
    }

    public boolean returnBoolean()
    {
        return returnVal;
    }

    public void stopThread(boolean flag)
    {
        this.stopThrd = flag;
    }

    public void run()
    {
        while(!stopThrd)
        {
            if (pad2.b)
            {
                while(pad2.b)
                {

                }
                returnVal = true;
                //run stuff
            }
        }
    }
}
