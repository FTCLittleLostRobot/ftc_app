/* Little Lost Robots
   Core Devs: Danielle
*/

//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmDropEncoderShiftLeft {


    DcMotor arm = null;
    Telemetry telemetry;
    int startValue = 1445;
    boolean recordReadings = false;
    int shiftValue = 4;
    boolean isShifting = false;

    public void init(DcMotor armToMove, Telemetry telemetry, boolean recordReadings){

        this.telemetry = telemetry;
        this.arm = armToMove;
        this.recordReadings = recordReadings;
        this.shiftValue = shiftValue;

        if (armToMove != null) {
            arm.setPower(0);
            armToMove.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            startValue = armToMove.getCurrentPosition();
        }
    }

    public void ArmDrop(double yPosition ) {

        if (arm != null) {
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            int encoderRangeValue = arm.getCurrentPosition();

            if (yPosition > -.75 && yPosition < .75) {
                isShifting = false;
                return;
            }

            if (isShifting == false) {

                if (yPosition >= .75) {
                    isShifting = true;
                    shiftValue = (shiftValue + 1);

                } else if (yPosition <= -0.75) {
                    isShifting = true;
                    shiftValue = (shiftValue - 1);
                }
            }

            if (shiftValue >= 5) {
                shiftValue = 4;
            }

            if (shiftValue <= 0) {
                shiftValue = 1;
            }


            if (shiftValue == 4) {
                encoderRangeValue = 1445;

            }

            if (shiftValue == 3) {
                encoderRangeValue = 1143;

            }


            if (shiftValue == 2) {
                encoderRangeValue = 533;

            }
            if (shiftValue == 1) {
                encoderRangeValue = 530;

            }

    /*    // Convert the joystick -1 to 1 range to a # of units of movement from 0 to 2.
        double positionInUnits = yPosition + 1.0;

        // Remap the Y value (from 0-2 range) to be within the 0-640(or whatever) encoder range
        // treating this like two ratios and solving for x (in this case, y)
        int encoderRangeValue = (int) ((positionInUnits * this.maxValue) / 2.0); */

            // The target value expected is now determined, but the motor is always inc/decreasing
            // so we need to shift the target value to be the motor's initial  starting position -
            // the new value
            int newPosition = encoderRangeValue - this.startValue;

            if (yPosition <= 0.1 && yPosition >= -0.1) {
                arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                arm.setPower(0);
                // do nothing
            } else {
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setTargetPosition(newPosition);
                arm.setPower(0.1);
            }


                telemetry.addData("Y Position", yPosition);
                telemetry.addData("Get Current Postion", arm.getCurrentPosition());
                telemetry.addData("Shift value", shiftValue);
                telemetry.addData("isShifting", isShifting);
                //telemetry.addData("1 - Position in Units", positionInUnits);
                // telemetry.addData("2 - Encoder Range Value", encoderRangeValue);
                //  telemetry.addData("3 - New Position", newPosition);

        }
    }

    public boolean IsDone() {
        return (!arm.isBusy());
    }

    public void Complete() {
        // Stop all motion;
        arm.setPower(0);
    }


}
