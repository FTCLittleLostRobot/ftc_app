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
    static final double GO_RIGHT = -1;
    static final double GO_LEFT = 1;
    static final double GO_HALFFOWARD = -0.5;
    static final double GO_BETWEENFOWARD = -0.95;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    public int foundColumn = -1;
    SamplingStateMachine.RobotState state;

    enum RobotState
    {
        Start,
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        CheckForGold,
        PushBloock,
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
        state = SamplingStateMachine.RobotState.CheckScreen;
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

            /**
             * Huge Problem: When the camera stops seeing the image it immediately breaks out of the operation it's doing.
             * I need to find a way to fix this problem.
             *
             * I'm also going to try and code a way to make it so that when the camera stops seeing the image it just keeps going or goes to -1
             * which will start the loop over. However, it would be much better if the camera just kept going and if it saw the object again it would go
             * to that column and go from there but still maintain how much inches left to drive.
             */
            case CheckForGold:
                //In this the robot is checking the phone for what column the yellow square is in
                if (foundColumn == 0 )
                {
                    this.moveRobot.Start(30, 60, GO_LEFT, GO_BETWEENFOWARD,0 );
                    state = SamplingStateMachine.RobotState.PushBloock;
                }
                else if (foundColumn == 1 )
                {
                    this.moveRobot.Start(30, 60, GO_LEFT, GO_BETWEENFOWARD,0 );
                    state = SamplingStateMachine.RobotState.PushBloock;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(30, 60,0, GO_FORWARD,0 );
                    state = SamplingStateMachine.RobotState.PushBloock;
                }
                else if (foundColumn == 3 || foundColumn == 4)
                {
                    this.moveRobot.Start(30, 60, GO_RIGHT, GO_BETWEENFOWARD,0 );
                    state = SamplingStateMachine.RobotState.PushBloock;
                }
                else if (foundColumn == -1 ) {
                    state = SamplingStateMachine.RobotState.CheckScreen;
                    break;
                }

            case PushBloock:
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