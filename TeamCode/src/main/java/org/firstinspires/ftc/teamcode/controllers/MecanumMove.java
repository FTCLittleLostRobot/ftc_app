/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class MecanumMove {

    public static final double GO_FORWARD = -1;
    public static final double GO_BACK = 1;
    public static final double GO_RIGHT = -1;
    public static final double GO_LEFT = 1;

    HardwareMecanumBase hwBase;

    int targetSpin = 0;
    int targetEncoderValue = 0;

    public void init(HardwareMecanumBase hwBase){
        this.hwBase = hwBase;
    } 
        
    public void Start(int speed, double inches, double x, double y, double rotation) {

        targetSpin  = this.hwBase.GetWheelSpinDirection(HardwareMecanumBase.WheelControl.LeftFrontDrive,x,y,rotation);


        if (hwBase.left_front_drive != null){
            // Determine new target position, and pass to motor controller
            int newLeftFrontTarget = this.hwBase.left_front_drive.getCurrentPosition() +
                    (targetSpin * (int) (inches * HardwareMecanumBase.WHEEL_COUNTS_PER_INCH));
            targetEncoderValue = newLeftFrontTarget;

        }

        this.hwBase.SpeedMultiplier = speed;
        this.hwBase.MoveMecanum(x,y,rotation);
    }

    public boolean IsDone() {
        boolean isDone;

        if (hwBase.left_front_drive != null){
            // this tells the robot if it is positive or negative
            if(targetSpin > 0){
                isDone = this.hwBase.left_front_drive.getCurrentPosition() >= targetEncoderValue; // if it is positive
            }
            else {
                isDone = this.hwBase.left_front_drive.getCurrentPosition() <= targetEncoderValue; // if it is negative
            }

            if (targetSpin > 0) {
                isDone = this.hwBase.left_front_drive.getCurrentPosition() >= targetEncoderValue; // if it is positive
            } else {
                isDone = this.hwBase.left_front_drive.getCurrentPosition() <= targetEncoderValue; // if it is negative
            }
        }
        else {
            isDone = true;
        }
        return isDone;
    }

    public void Complete() {
        // Stop all motion;
        this.hwBase.ResetMotors();
    }
}



