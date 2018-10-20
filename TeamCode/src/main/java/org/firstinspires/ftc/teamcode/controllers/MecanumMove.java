package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.HardwareTestingBase;

public class MecanumMove {

    HardwareMecanumBase hwBase;

    public void init(HardwareMecanumBase hwBase){
        this.hwBase = hwBase;
    } 
        
    public void Start(double speed, double inches) {

        int newLeftTarget;
        int newRightTarget;
     
        // Determine new target position, and pass to motor controller
        newLeftTarget = this.hwBase.left_front_drive.getCurrentPosition() + (int) (inches * HardwareMecanumBase.COUNTS_PER_INCH);
        newRightTarget = this.hwBase.right_front_drive.getCurrentPosition() + (int) (inches * HardwareMecanumBase.COUNTS_PER_INCH);

        this.hwBase.DriveMotorToPostion(newRightTarget, newLeftTarget);
        this.hwBase.SpeedMultiplier = 50;
        this.hwBase.MoveMecanum(0,-1,0);
    }

    public boolean IsDone() {
        return !(this.hwBase.left_front_drive.isBusy() && this.hwBase.right_front_drive.isBusy());
    }

    public void Complete() {
        // Stop all motion;
        this.hwBase.ResetMotors();

    }
}



