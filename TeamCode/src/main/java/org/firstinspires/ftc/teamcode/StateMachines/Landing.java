/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import org.firstinspires.ftc.robotcore.external.StateMachine;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;

public class Landing {

    HardwareMecanumBase hwBase;
    Telemetry telemetry;
    private LanderEncoder lander    = new LanderEncoder();


    public void init(HardwareMecanumBase hwBase, Telemetry telemetry) {

        this.hwBase = hwBase;
        this.telemetry = telemetry;

    }

    public void ProcessState(RobotState state)
    {
        switch (state) {
            case Drop:
                this.lander.DoLand(6);
                state = RobotState.WaitForDrop;
                break;

            case WaitForDrop:
                if (this.lander.IsDone()) {
                    this.lander.Complete();
                    state = RobotState.Done;
                }
                break;
        }
    }
}