/* Little Lost Robots
   Core Devs: Danielle
*/

//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class LanderNoEncoder {

    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;

    }

    public void DoLand( ) {
       if (hwBase.lift != null ) {
           hwBase.lift.setPower(1);
           telemetry.addData("State A", "Robot is Going Down");
           telemetry.update();
        }
    }

    public void GoUp() {
        if (hwBase.lift != null ) {
            hwBase.lift.setPower(-1);
            telemetry.addData("State B", "Robot is Going Up");
            telemetry.update();
        }
    }


    public void Complete() {
        // Stop all motion;
        if (hwBase.lift != null)
        {
            hwBase.lift.setPower(0);
            //telemetry.addData("Lift", " Lift is complete");

        }
    }
}
