/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class LandingStateMachine {

    Telemetry telemetry;
    LandingStateMachine.RobotState state;
    private LanderEncoder lander    = null;
    private MecanumMove moveRobot    = null;
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware
  //  ColorFindStateMachine colorFinderStateMachine = null;
    private int firstFoundColumn = -1;

    private static enum RobotState {
        Start,
        Drop,
        WaitForDrop,
        CheckInitalColumn,
        WaitForInitalCheck,
        Unhook,
        WaitForUnhook,
        CheckForFinalColumn,
        WaitForFinalColumn,
        Done
    }

    public void init(Telemetry telemetry, LanderEncoder lander, MecanumMove mecanumMove, HardwareMecanumBase robot) {

        this.telemetry = telemetry;
        this.lander = lander;
        this.moveRobot = mecanumMove;
        state = RobotState.Start;
        this.robot = robot;

//        ColorFinder colorFinder = new ColorFinder();
//        colorFinder.init(robot.hardwareMap);
//        this.colorFinderStateMachine = new ColorFindStateMachine();
//        this.colorFinderStateMachine.init(telemetry, colorFinder);
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
                this.lander.DoLand(2);
                state = RobotState.WaitForDrop;
                break;

            case WaitForDrop:
                if (this.lander.IsDone()) {
                    this.lander.Complete();
                    state = RobotState.Unhook;
                }
                break;
/*
            case CheckInitalColumn:
                this.colorFinderStateMachine.Start();
                state = RobotState.WaitForInitalCheck;
                break;

            case WaitForInitalCheck:
                colorFinderStateMachine.ProcessState();
                if (this.colorFinderStateMachine.IsDone()){
                    this.firstFoundColumn = this.colorFinderStateMachine.GetFoundColumn();
                    state = RobotState.Unhook;
                }
                break;
*/
            case Unhook:
                this.moveRobot.Start(30, 2,MecanumMove.GO_RIGHT,0,0 );
                state = RobotState.WaitForUnhook;
                break;

            case WaitForUnhook:
                if (this.moveRobot.IsDone()){
                    this.moveRobot.Complete();
                    state = RobotState.Done;
                }
                break;
/*
            case CheckForFinalColumn:
                this.colorFinderStateMachine.Start();
                state = RobotState.WaitForFinalColumn;
                break;

            case WaitForFinalColumn:
                this.colorFinderStateMachine.ProcessState();
                if (this.colorFinderStateMachine.IsDone()) {
                    int finalColumn = this.colorFinderStateMachine.GetFoundColumn();
                    if (finalColumn != this.firstFoundColumn)
                    {
                        state = RobotState.Done;
                    }
                    else
                    {
                        state = RobotState.Unhook;
                    }
                }

                break;
*/        }
    }
}