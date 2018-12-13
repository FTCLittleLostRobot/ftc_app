/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ArmExtend;

public class ArmExtendStateMachine {

    Telemetry telemetry;
    ArmExtendStateMachine.RobotState state;
    private ArmExtend armExtend = new ArmExtend();
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware

    private static enum RobotState {
        Start,
        Extend,
        WaitForExtend,
        Done
    }

    public void init(Telemetry telemetry, HardwareMecanumBase robot, ArmExtend armExtend) {

        this.telemetry = telemetry;
        state = RobotState.Start;
        this.robot = robot;
        this.armExtend.init(robot, telemetry);

    }

    public void Start()
    {
        state = RobotState.Extend;
    }

    public boolean IsDone()
    {
        return (state == RobotState.Done);
    }

    public void ProcessState()
    {
        telemetry.addData("Current Extending State", state.toString());

        switch (state) {

            case Extend:
                this.armExtend.ExtendingArm(5000, 0.1);
                state = RobotState.WaitForExtend;
                break;

            case WaitForExtend:
                if (this.armExtend.IsDone()) {
                    this.armExtend.Complete();
                    state = RobotState.Done;
                }
                break;

            case Done:
                state = RobotState.Done;
                break;
        }
    }
}