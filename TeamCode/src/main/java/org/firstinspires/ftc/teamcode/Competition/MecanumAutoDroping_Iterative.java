/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.Competition;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Image;

import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

import static java.lang.Thread.sleep;

@Autonomous(name="Mecanum: Dropping", group="Mecanum")
public class MecanumAutoDroping_Iterative extends OpMode {

    HardwareMecanumBase robot;

    MecanumMove moveRobot;
    ColorFinder colorFinder;
    private LanderEncoder lander    = new LanderEncoder();

    static final double GO_FORWARD = -1;
    static final double GO_BACK = 1;
    static final double GO_RIGHT = -1;
    static final double GO_LEFT = 1;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    public int foundColumn = -1;

    enum RobotState
    {
        Drop,
        WaitForDrop,
        Unhook,
        WaitForUnhook,
        Up,
        WaitForUp,
        StepOut,
        WaitForStepOut,
        StrafingLeft,
        WaitForStrafeLeft,
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        CheckForGold,
        WaitForScoot,
        PushBloock,
        Done,

    }

    RobotState state;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot = new HardwareMecanumBase();
        moveRobot = new MecanumMove();
        colorFinder = new ColorFinder();

        robot.init(hardwareMap);
        this.moveRobot.init(robot);
        this.lander.init(robot, telemetry);
        this.colorFinder.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        state = RobotState.Drop;
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Current State", state.toString());

        switch (state)
        {
            case Drop:
                this.lander.DoLand(6);
                state = RobotState.WaitForDrop;
                break;

            case WaitForDrop:
                if (this.lander.IsDone()) {
                    this.lander.Complete();
                    state = RobotState.Done;
                }
                break;

        /*    case Up:
                this.lander.GoUp(3);
                state = RobotState.WaitForUp;
                break;

            case WaitForUp:
                if (this.lander.IsDone()) {
                    this.lander.Complete();
                    state = RobotState.Unhook;
                }


            case Unhook:
                this.moveRobot.Start(30, 4,GO_LEFT,0,0 );
                state = RobotState.WaitForUnhook;
                break;

            case WaitForUnhook:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.Done;
                }
                break;

            case StrafingLeft:
                this.moveRobot.Start(30, 15,GO_LEFT,0,0 );
                state = RobotState.WaitForStrafeLeft;
                break;

            case WaitForStrafeLeft:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.CheckScreen;
                }
                break;

            case StepOut:
                this.moveRobot.Start(30, 18,0,GO_FORWARD,0 );
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
                    this.moveRobot.Start(50, 2,GO_LEFT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 1 )
                {
                    this.moveRobot.Start(30, 1,GO_LEFT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 3 )
                {
                    this.moveRobot.Start(30, 1,GO_RIGHT,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 4 ) {
                    this.moveRobot.Start(50, 2, GO_RIGHT, 0, 0);
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(50, 24,0,GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == -1)
                {
                    // if not found
                    this.moveRobot.Start(30, 3,GO_RIGHT,0,0 );
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
            */
            case Done:
                state = RobotState.Done;
                break;
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}