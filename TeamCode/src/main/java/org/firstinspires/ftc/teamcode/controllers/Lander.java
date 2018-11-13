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
        //if null = false(hwBase.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //if null (
                telemetry.addLine();
    }

    public void DoLand(int inches ) {
        hwBase.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = hwBase.lift.getCurrentPosition() - (int)(inches * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(1);
        telemetry.addData("State A", "Going Down");
        telemetry.update();
    }

    public void GoUp(int inches) {
        hwBase.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = hwBase.lift.getCurrentPosition() + (int)(inches * HardwareMecanumBase.LIFT_COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(-0.9);
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
