/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware

    private static enum RobotState {
        Start,
       // RaiseArm,
        Drop,
        WaitForDrop,
        Unhook,
        WaitForUnhook,
        Done
    }

    public void init(Telemetry telemetry, LanderEncoder lander, MecanumMove mecanumMove, HardwareMecanumBase robot) {

        this.telemetry = telemetry;
        this.lander = lander;
        this.moveRobot = mecanumMove;
        state = RobotState.Start;
        this.robot = robot;
    }

    public void Start()
    {
        state = RobotState.Drop;
    }

 /*   public void RaiseArm() {
        robot.ArmDropLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmDropLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int newLiftTarget = robot.ArmDropLeft.getCurrentPosition();
        robot.ArmDropLeft.setTargetPosition(252);
        robot.ArmDropLeft.setPower(0.75);
        telemetry.addData("ArmDrop", "Left is going to position");
        telemetry.update();
        state = RobotState.Drop;
    }*/
    public boolean IsDone()
    {
        return (state == RobotState.Done);
    }

    public void ProcessState()
    {
        telemetry.addData("Current Landing State", state.toString());

        switch (state) {
            /*case RaiseArm:
                robot.ArmDropLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.ArmDropLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                int newLiftTarget = robot.ArmDropLeft.getCurrentPosition();
                robot.ArmDropLeft.setTargetPosition(252);
                robot.ArmDropLeft.setPower(0.75);
                telemetry.addData("ArmDrop", "Left is going to position");
                telemetry.update();
                state = RobotState.Drop;
                break;
                */
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

            case Unhook:
                this.moveRobot.Start(30, 2,MecanumMove.GO_RIGHT,0,0 );
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