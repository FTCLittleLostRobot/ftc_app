/* Little Lost Robots
   Core Devs: Danielle
*/

//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class ArmExtend {


    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;

        if (hwBase.ArmExtendLeft != null) {
            hwBase.ArmExtendLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (hwBase.ArmExtendRight != null) {
            hwBase.ArmExtendRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void DoLand(double inches ) {
        hwBase.ArmExtendLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hwBase.ArmExtendRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int newArmExtentLeft = hwBase.ArmExtendLeft.getCurrentPosition() - (int)(inches * HardwareMecanumBase.WHEEL_COUNTS_PER_INCH);
        hwBase.ArmExtendLeft.setTargetPosition(newArmExtentLeft);
        hwBase.ArmExtendLeft.setPower(0.5);
        telemetry.addData("ArmExtend", " Left Arm is Extending");
        telemetry.update();

        int newArmExtentRight = hwBase.ArmExtendRight.getCurrentPosition() - (int)(inches * HardwareMecanumBase.WHEEL_COUNTS_PER_INCH);
        hwBase.ArmExtendRight.setTargetPosition(newArmExtentRight);
        hwBase.ArmExtendRight.setPower(0.5);
        telemetry.addData("ArmExtend", " Right Arm is Extending");
        telemetry.update();
    }

    public void ArmExtend(int inches) {
        hwBase.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = hwBase.lift.getCurrentPosition() + (int)(inches * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(-0.9);
        telemetry.addData("State B", "Robot is Going Up");
        telemetry.update();
    }


    public boolean IsDone() {
        return (!hwBase.lift.isBusy());
    }

    public void Complete() {
        // Stop all motion;
        hwBase.lift.setPower(0);
    }
}
