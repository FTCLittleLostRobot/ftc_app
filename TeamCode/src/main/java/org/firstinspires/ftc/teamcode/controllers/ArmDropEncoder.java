/* Little Lost Robots
   Core Devs: Danielle
*/

//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class ArmDropEncoder {


    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;

        if (hwBase.ArmDropLeft != null) {
            hwBase.ArmDropLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (hwBase.ArmDropRight != null) {
            hwBase.ArmDropRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void ArmDropRight(double inches ) {
        hwBase.ArmDropRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newArmDropRightTarget = hwBase.ArmDropRight.getCurrentPosition() - (int)(inches * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.ArmDropRight.setTargetPosition(newArmDropRightTarget);
        hwBase.ArmDropRight.setPower(0.01);
        telemetry.addData("ArmDropTarget", newArmDropRightTarget);
        telemetry.addData("ArmDrop", "Right arm is moving");
        telemetry.update();
    }

    public void ArmDropLeft(int inches) {
        hwBase.ArmDropLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newArmDropLeftTarget = hwBase.ArmDropLeft.getCurrentPosition() + (int)(inches * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.ArmDropLeft.setTargetPosition(newArmDropLeftTarget);
        hwBase.ArmDropLeft.setPower(0.01);
        telemetry.addData("ArmDrop", "Left arm is moving");
        telemetry.update();
    }

    public boolean IsDoneRight() {
        return (!hwBase.ArmDropRight.isBusy());
    }

    public boolean IsDoneLeft() {
        return (!hwBase.ArmDropLeft.isBusy());
    }

    public void Complete() {
        // Stop all motion;
        hwBase.ArmDropLeft.setPower(0);
        hwBase.ArmDropRight.setPower(0);
    }


}
