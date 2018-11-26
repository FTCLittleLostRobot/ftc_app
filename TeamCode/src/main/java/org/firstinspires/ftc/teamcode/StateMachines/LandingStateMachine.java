/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class LandingStateMachine {

    Telemetry telemetry;
    LandingStateMachine.RobotState state;
    private LanderEncoder lander    = null;
    private MecanumMove moveRobot    = null;

    private static enum RobotState {
        Start,
        Drop,
        WaitForDrop,
        Unhook,
        WaitForUnhook,
        Done
    }

    public void init(Telemetry telemetry, LanderEncoder lander, MecanumMove mecanumMove) {

        this.telemetry = telemetry;
        this.lander = lander;
        this.moveRobot = mecanumMove;
        state = RobotState.Start;
    }

    public void Start()
    {
        state = RobotState.Drop;
    }

    public boolean IsDone()
    {
        return (state == RobotState.Done);
    }

    public void ProcessState()
    {
        telemetry.addData("Current Landing State", state.toString());

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

            case Done:
                state = RobotState.Done;
                break;
        }
    }
}