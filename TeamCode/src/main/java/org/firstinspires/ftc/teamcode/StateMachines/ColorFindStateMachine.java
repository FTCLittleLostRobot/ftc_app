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

public class ColorFindStateMachine {

    Telemetry telemetry;
    ColorFinder colorFinder = null;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    private int foundColumn = -1;
    ColorFindStateMachine.RobotState state;

    enum RobotState
    {
        Start,
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        Done
    }

    public void init(Telemetry telemetry, ColorFinder colorFinder) {

        this.telemetry = telemetry;
        this.colorFinder = colorFinder;

        telemetry.addData("Say", "Hello Driver");    //
        state = ColorFindStateMachine.RobotState.Start;
    }

    public void Start()
    {
        state = ColorFindStateMachine.RobotState.CheckScreen;
    }

    public boolean IsDone()
    {
        return (state == ColorFindStateMachine.RobotState.Done);
    }

    public int GetFoundColumn(){return foundColumn;}


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
                state = ColorFindStateMachine.RobotState.ConvertImageFromScreen;
                break;

            case ConvertImageFromScreen:
                try {
                    bitmapFromVuforia = colorFinder.getBitmapToAnalyze(vuforiaImageObject);
                }
                catch(InterruptedException ex) {
                    telemetry.addData("error", ex.getMessage());
                    break;
                }
                state = ColorFindStateMachine.RobotState.DetectColorFromImage;
                break;

            case DetectColorFromImage:
                foundColumn =  colorFinder.FindColor(bitmapFromVuforia, ColorFinder.ColorTarget.Yellow);
                telemetry.addData("column", foundColumn);
                state = ColorFindStateMachine.RobotState.Done;
                break;

        }
    }
}
