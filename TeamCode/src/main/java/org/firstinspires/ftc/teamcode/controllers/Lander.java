//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class Lander {


    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;
        hwBase.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void DoLand() {
        hwBase.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = hwBase.lift.getCurrentPosition() + (int)(5 * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(0.85);
        telemetry.addData("State A", "Going Down");
        telemetry.update();
    }

    public void GoUp() {
        hwBase.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = hwBase.lift.getCurrentPosition() - (int)(5 * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(-0.5);
        telemetry.addData("State B", "Going Up");
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
