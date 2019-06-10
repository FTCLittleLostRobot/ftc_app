package org.firstinspires.ftc.teamcode.StateMachines;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ArmExtend;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class DabEmoteStateMachine {

    Telemetry telemetry;
    DabEmoteStateMachine.RobotState state;
    private ArmExtend armExtend = new ArmExtend();
    MecanumMove moveRobot;
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware

    private static enum RobotState {
        Start,
        Extend,
        WaitForExtend,
        ExtendDiagonal,
        WaitForExtendDiagonal,
        Done
    }

    public void init(Telemetry telemetry, HardwareMecanumBase robot, ArmExtend armExtend, MecanumMove mecanumMove) {

        this.telemetry = telemetry;
        state = DabEmoteStateMachine.RobotState.Start;
        this.robot = robot;
        this.armExtend.init(robot, telemetry);
        this.moveRobot = mecanumMove;

        telemetry.addData("Say", "Hello Driver");    //
        state = DabEmoteStateMachine.RobotState.Start;

    }

    public void Start() {
        state = DabEmoteStateMachine.RobotState.Extend;
    }

    public boolean IsDone() {
        return (state == DabEmoteStateMachine.RobotState.Done);
    }

    public void ProcessState() {
        telemetry.addData("Current Extending State", state.toString());

        switch (state) {

            case Extend:
                this.armExtend.ExtendingArm(5000, 0.1);
                state = DabEmoteStateMachine.RobotState.WaitForExtend;
                break;

            case WaitForExtend:
                if (this.armExtend.IsDone()) {
                    this.armExtend.Complete();
                    state = DabEmoteStateMachine.RobotState.Done;
                }
                break;

            case ExtendDiagonal:
                this.armExtend.ExtendingArm(5000, 0.1);
                state = DabEmoteStateMachine.RobotState.WaitForExtend;
                break;

            case WaitForExtendDiagonal:
                if (this.armExtend.IsDone()) {
                    this.armExtend.Complete();
                    state = DabEmoteStateMachine.RobotState.Done;
                }
                break;

            case Done:
                state = DabEmoteStateMachine.RobotState.Done;
                break;
        }
    }
}
