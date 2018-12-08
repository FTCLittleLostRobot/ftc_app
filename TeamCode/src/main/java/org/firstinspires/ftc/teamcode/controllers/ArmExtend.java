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
    int startValue = 0;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;

        if (hwBase.ArmExtend != null) {
            hwBase.ArmExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            startValue = hwBase.ArmExtend.getCurrentPosition();
        }


    }

    public void ExtendingArm(double encoderTicks, double power ) {
        hwBase.ArmExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        int newArmExtend = hwBase.ArmExtend.getCurrentPosition() - (int)(encoderTicks);
        hwBase.ArmExtend.setTargetPosition(newArmExtend);
        hwBase.ArmExtend.setPower(power);
        telemetry.addData("ArmExtend", " Arm is Extending");
        telemetry.addData("Start Value", startValue);
        telemetry.addData("Current Value", newArmExtend);

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
