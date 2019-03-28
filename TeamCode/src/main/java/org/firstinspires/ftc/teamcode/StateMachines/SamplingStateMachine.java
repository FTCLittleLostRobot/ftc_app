/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import android.graphics.Bitmap;

import com.vuforia.Image;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class SamplingStateMachine {

    Telemetry telemetry;
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware
    ColorFinder colorFinder = null;
    MecanumMove moveRobot;
    static final double FORWARD_SPEED = 0.1;
    static final double TURN_SPEED = 0.25;
    static final double GO_FORWARD = -1;
    static final double GO_BACK = 1;
    static final double GO_RIGHT = -0.95;
    static final double GO_LEFT = 1.9;
    static final double GO_BETWEENLEFT = 1.05;
    static final double GO_HALFFOWARD = -0.5;
    static final double GO_BETWEENFOWARD = -0.94;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    public int foundColumn = -1;
    SamplingStateMachine.RobotState state;

    enum RobotState
    {
        Start,
        StepOut,
        SteppingOut,
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        CheckForGold,
        PushBlock,
        StrafeRight,
        WaitForStrafingRight,
        PushFoward,
        WaitForPushFoward,
        PushToCrater,
        PushingToCrater,
        WaitForBackOff,
        Done
    }

    public void init(Telemetry telemetry, ColorFinder colorFinder, MecanumMove mecanumMove) {

        this.telemetry = telemetry;
        this.colorFinder = colorFinder;
        this.moveRobot = mecanumMove;

        telemetry.addData("Say", "Hello Driver");    //
        state = SamplingStateMachine.RobotState.Start;
    }

    public void Start()
    {
        state = SamplingStateMachine.RobotState.StepOut;
    }

    public boolean IsDone()
    {
        return (state == SamplingStateMachine.RobotState.Done);
    }

    public void ProcessState()
    {
        telemetry.addData("Current State", state.toString());

        switch (state)
        {
            case StepOut:
                this.moveRobot.Start(20, 0.25, GO_LEFT, GO_BETWEENFOWARD, 0 );
                state = SamplingStateMachine.RobotState.SteppingOut;

            case SteppingOut:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = SamplingStateMachine.RobotState.CheckScreen;
                }
                break;

            case CheckScreen:
                try {
                    vuforiaImageObject = colorFinder.getVuforiaImagefromFrame();
                }
                catch(InterruptedException ex){
                    telemetry.addData("error", ex.getMessage());
                    break;
                }
                state = SamplingStateMachine.RobotState.ConvertImageFromScreen;
                break;

            case ConvertImageFromScreen:
                try {
                    bitmapFromVuforia = colorFinder.getBitmapToAnalyze(vuforiaImageObject);
                }
                catch(InterruptedException ex) {
                    telemetry.addData("error", ex.getMessage());
                    break;
                }
                state = SamplingStateMachine.RobotState.DetectColorFromImage;
                break;

            case DetectColorFromImage:
                foundColumn =  colorFinder.FindColor(bitmapFromVuforia, ColorFinder.ColorTarget.Yellow);
                telemetry.addData("column", foundColumn);
                state = SamplingStateMachine.RobotState.CheckForGold;
                break;

            case CheckForGold:
                //In this the robot is checking the phone for what column the yellow square is in
                if (foundColumn == 0  || foundColumn == 1)
                {
                    this.moveRobot.Start(30, 22, GO_BETWEENLEFT, GO_FORWARD,0 );
                    state = SamplingStateMachine.RobotState.WaitForPushFoward;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(30, 32,0, GO_FORWARD,0 );
                    state = SamplingStateMachine.RobotState.WaitForPushFoward;
                }
                else if (foundColumn == 3 || foundColumn == 4)
                {
                    this.moveRobot.Start(30, 20, 0,GO_FORWARD,0 );
                    state = SamplingStateMachine.RobotState.PushBlock;
                }
                else if (foundColumn == -1 ) {
                    state = SamplingStateMachine.RobotState.CheckScreen;
                    break;
                }

            case PushBlock:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = SamplingStateMachine.RobotState.StrafeRight;
                }
                break;

            case StrafeRight:
                this.moveRobot.Start(30, 15, GO_RIGHT,0, 0 );
                state = SamplingStateMachine.RobotState.WaitForStrafingRight;

            case WaitForStrafingRight:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = SamplingStateMachine.RobotState.PushFoward;
                }
                break;

            case PushFoward:
                this.moveRobot.Start(30, 10, 0, GO_FORWARD, 0 );
                state = SamplingStateMachine.RobotState.WaitForPushFoward;

            case WaitForPushFoward:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = SamplingStateMachine.RobotState.PushToCrater;
                }
                break;

            case PushToCrater:
                this.moveRobot.Start(30, 10, 0, GO_FORWARD, 0 );
                state = SamplingStateMachine.RobotState.PushingToCrater;

            case PushingToCrater:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = SamplingStateMachine.RobotState.Done;
                }
                break;

            case Done:
                state = SamplingStateMachine.RobotState.Done;
                break;
        }
    }
}
