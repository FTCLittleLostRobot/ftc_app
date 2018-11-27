/* Little Lost Robots
   Core Devs: Caden
*/


package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Image;

import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

@Autonomous(name="TestingBase: Find Block Iterative Test", group="TestingBase")
public class MecanumTestCameraTeleop_Iterative extends OpMode {

    /* Declare OpMode members. */
    HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware
    ColorFinder colorFinder = null;
    MecanumMove moveRobot;


    static final double FORWARD_SPEED = 0.1;
    static final double TURN_SPEED = 0.25;
    static final double GO_FORWARD = -1;
    static final double GO_BACK = 1;
    static final double GO_RIGHT = -1;
    static final double GO_LEFT = 1;
    private Image vuforiaImageObject;
    private Bitmap bitmapFromVuforia;
    public int foundColumn = -1;


    enum RobotState
    {
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        CheckForGold,
        PushBloock,
        Done
    }
    RobotState state = RobotState.CheckScreen;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        int newLeftTarget;
        int newRightTarget;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        this.robot.init(hardwareMap);
        this.moveRobot = new MecanumMove();
        this.colorFinder = new ColorFinder();

        this.colorFinder.init(hardwareMap);
        this.moveRobot.init(robot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {}

    @Override
    public void loop() {

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
                /**
                 * Huge Problem: When the camera stops seeing the image it immediately breaks out of the operation it's doing.
                 * I need to find a way to fix this problem.
                 *
                 * I'm also going to try and code a way to make it so that when the camera stops seeing the image it just keeps going or goes to -1
                 * which will start the loop over. However, it would be much better if the camera just kept going and if it saw the object again it would go
                 * to that column and go from there but still maintain how much inches left to drive.
                 */
                if (foundColumn == 0 )
                {
                    this.moveRobot.Start(50, 24, GO_LEFT, GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == 1 )
                {
                    this.moveRobot.Start(30, 24, GO_LEFT, GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(50, 30,0, GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == 3 )
                {
                    this.moveRobot.Start(30, 24, GO_RIGHT, GO_FORWARD,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == 4 ) {
                    this.moveRobot.Start(50, 24, GO_RIGHT, GO_FORWARD, 0);
                    state = RobotState.PushBloock;
                }

                else if (foundColumn == -1 ) {
                    {
                        this.moveRobot.Start(30, 4, GO_RIGHT, 0, 0);
                        state = RobotState.PushBloock;
                    }

                    if (foundColumn == -1) {
                        this.moveRobot.Start(30, 8, GO_LEFT, 0, 0);
                        state = RobotState.PushBloock;
                        // When the camera sees nothing, it will go to this area and then go back to CheckScreen, until it finds the object it's looking for
                    }
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
        telemetry.addData("Current State", state.toString());
        telemetry.update();
    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

