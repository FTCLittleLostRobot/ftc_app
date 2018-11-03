//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.HardwareTestingBase;

public class Lander {


    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;
    }

    public void DoLand() {
        int newLiftTarget = hwBase.lift.getCurrentPosition() + (int)(1 * HardwareTestingBase.COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(.2);
        telemetry.addData("State A", "Going Down");
        telemetry.update();
    }

    public void GoUp() {
        int newLiftTarget = hwBase.lift.getCurrentPosition() - (int)(1 * HardwareTestingBase.COUNTS_PER_INCH);
        hwBase.lift.setTargetPosition(newLiftTarget);
        hwBase.lift.setPower(-0.2);
        telemetry.addData("State B", "Going Up");
        telemetry.update();
    }


    public boolean IsDone() {
        return !hwBase.lift.isBusy();
    }

}
