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

        if (hwBase.ArmExtend != null) {
            hwBase.ArmExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }


    }

    public void ExtendingArm(double inches ) {
        hwBase.ArmExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        int newArmExtentLeft = hwBase.ArmExtend.getCurrentPosition() - (int)(inches * HardwareMecanumBase.WHEEL_COUNTS_PER_INCH);
        hwBase.ArmExtend.setTargetPosition(newArmExtentLeft);
        hwBase.ArmExtend.setPower(0.5);
        telemetry.addData("ArmExtend", " Arm is Extending");
        telemetry.update();
    }




    public boolean IsDone() {
        return (!hwBase.ArmExtend.isBusy());
    }

    public void Complete() {
        // Stop all motion;
        hwBase.ArmExtend.setPower(0);
    }
}
