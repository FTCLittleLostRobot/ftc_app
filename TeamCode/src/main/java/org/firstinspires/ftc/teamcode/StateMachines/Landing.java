/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import org.firstinspires.ftc.robotcore.external.StateMachine;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class Landing {

    Telemetry telemetry;
    private LanderEncoder lander    = null;
    private MecanumMove moveRobot    = null;



    public void init(HardwareMecanumBase hwBase, Telemetry telemetry) {

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
                    state = RobotState.Unhook;
                }
                break;

            case Unhook:
                this.moveRobot.Start(30, 2,MecanumMove.GO_LEFT,0,0 );
                state = RobotState.WaitForUnhook;
                break;

            case WaitForUnhook:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.Done;
                }
                break;

        }
    }
}