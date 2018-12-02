/* Little Lost Robots
   Core Devs: Danielle
*/

//Core Devs: Danielle

//todo need to go up 22 up (bottom of handal) 23.5 (top of handal)
package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;

public class ArmDropNoEncoder {

    HardwareMecanumBase hwBase;
    Telemetry telemetry;

    public void init(HardwareMecanumBase hwBase, Telemetry telemetry){

        this.hwBase = hwBase;
        this.telemetry = telemetry;

    }

    public void ArmDropLeft( ) {
       if (hwBase.ArmDropLeft != null ) {
           hwBase.ArmDropLeft.setPower(0.1);
           telemetry.addData(" ArmDrop", "Left Arm is moving");
           telemetry.update();
        }
    }

    public void ArmDropRight() {
        if (hwBase.ArmDropRight != null ) {
            hwBase.ArmDropRight.setPower(0.1);
            telemetry.addData("ArmDrop", "Right Arm is moving");
            telemetry.update();
        }
    }


    public void Complete() {
        // Stop all motion;
        if (hwBase.ArmDropLeft != null)
        {
            hwBase.ArmDropLeft.setPower(0);
            telemetry.addData("ArmDropLeft", " left Arm drop is complete");

        }
    }
}
