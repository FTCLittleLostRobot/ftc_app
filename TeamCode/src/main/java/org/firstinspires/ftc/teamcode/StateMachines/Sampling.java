/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.StateMachines;

import android.graphics.Bitmap;

import com.vuforia.Image;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

public class Sampling {

    Telemetry telemetry;
    private LanderEncoder lander    = null;
    private MecanumMove moveRobot    = null;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    public int foundColumn = -1;
    ColorFinder colorFinder;


    public void init(HardwareMecanumBase hwBase, Telemetry telemetry) {

        this.telemetry = telemetry;
        colorFinder = new ColorFinder();


    }

    public void ProcessState(RobotState state)
    {
        telemetry.addData("Current State", state.toString());

        switch (state) {
            case Unhook:
                this.moveRobot.Start(30, 4,MecanumMove.GO_LEFT,0,0 );
                state = RobotState.StrafingLeft;
                break;

            case WaitForUnhook:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.StrafingLeft;
                }
                break;

            case StrafingLeft:
                this.moveRobot.Start(30, 15,MecanumMove.GO_LEFT,0,0 );
                state = RobotState.WaitForStrafeLeft;
                break;

            case WaitForStrafeLeft:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.CheckScreen;
                }
                break;

            case StepOut:
                this.moveRobot.Start(30, 18,0,MecanumMove.GO_FORWARD,0 );
                state = RobotState.WaitForStepOut;
                break;

            case WaitForStepOut:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.StrafingLeft;
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
                state = RobotState.ConvertImageFromScreen;
                break;

            case ConvertImageFromScreen:
                try {
                    bitmapFromVuforia = colorFinder.getBitmapToAnalyze(vuforiaImageObject);
                }
                catch(InterruptedException ex) {
                    telemetry.addData("error", ex.getMessage());
                    break;
                }
                state = RobotState.DetectColorFromImage;
                break;

            case DetectColorFromImage:
                foundColumn =  colorFinder.FindColor(bitmapFromVuforia, ColorFinder.ColorTarget.Yellow);
                telemetry.addData("column", foundColumn);
                state = RobotState.CheckForGold;
                break;

            case CheckForGold:
                //In this the robot is checking the phone for what column the yellow square is in
                if (foundColumn == 0 )
                {
                    this.moveRobot.Start(50, 2,MecanumMove.GO_LEFT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 1 )
                {
                    this.moveRobot.Start(30, 1,MecanumMove.GO_LEFT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 3 )
                {
                    this.moveRobot.Start(30, 1,MecanumMove.GO_RIGHT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 4 ) {
                    this.moveRobot.Start(50, 2, MecanumMove.GO_RIGHT, 0, 0);
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(50, 24,0,MecanumMove.GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == -1)
                {
                    // if not found
                    this.moveRobot.Start(30, 3,MecanumMove.GO_RIGHT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                break;

            case WaitForScoot:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.CheckScreen;
                }
                break;

            case PushBloock:
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