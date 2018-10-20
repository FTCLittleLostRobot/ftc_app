package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.HardwareTestingBase;

public class MecanumMove {

    HardwareMecanumBase hwBase;

    int targetSpin = 0;
    int targetEncoderValue = 0;

    public void init(HardwareMecanumBase hwBase){
        this.hwBase = hwBase;
    } 
        
    public void Start(int speed, double inches, double x, double y, double rotation) {

        // Determine new target position, and pass to motor controller
        int newLeftFrontTarget = this.hwBase.left_front_drive.getCurrentPosition() + (int) (inches * HardwareMecanumBase.COUNTS_PER_INCH);
        targetSpin  = this.hwBase.GetWheelSpinDirection(HardwareMecanumBase.WheelControl.LeftFrontDrive,x,y,rotation);
        newLeftFrontTarget = newLeftFrontTarget * targetSpin;
        targetEncoderValue = newLeftFrontTarget;

        this.hwBase.SpeedMultiplier = speed;
        this.hwBase.MoveMecanum(x,y,rotation);
    }

    public boolean IsDone() {
        boolean isDone;
        if(targetSpin > 0){
            isDone = this.hwBase.left_front_drive.getCurrentPosition() >= targetEncoderValue;
        }
        else {
            isDone = this.hwBase.left_front_drive.getCurrentPosition() <= targetEncoderValue;
        }

        return isDone;
    }

    public void Complete() {
        // Stop all motion;
        this.hwBase.ResetMotors();
    }
}



