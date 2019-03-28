/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.Competition;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.StateMachines.LandingStateMachine;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.StateMachines.SamplingStateMachine;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

@Autonomous(name="Mecanum: Sampling and Dropping", group="Mecanum")
public class MecanumAutoSamplingAndDropping_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private SamplingStateMachine samplingStateMachine;
    private MecanumMove moveRobot;
    private ColorFinder colorFinder;
    private LandingStateMachine landingStateMachine;
    private LanderEncoder lander;



    @Override
    public void init() {
        /* Step 1: Setup of variables  */
        this.robot = new HardwareMecanumBase();
        this.colorFinder = new ColorFinder();
        this.moveRobot = new MecanumMove();
        this.samplingStateMachine = new SamplingStateMachine();
        this.landingStateMachine = new LandingStateMachine();
        this.lander = new LanderEncoder();

        /* Step 2: Setup of hardware  */
        robot.init(hardwareMap);

        /* Step 3: Setup of controllers  */
        this.colorFinder.init(hardwareMap);
        this.lander.init(robot, telemetry);
        this.moveRobot.init(robot);

        /* Step 4: Setup of state machines  */
        this.landingStateMachine.init(telemetry, this.lander, this.moveRobot, this.robot);
        this.samplingStateMachine.init(telemetry, colorFinder, moveRobot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    @Override
    public void start() {
        landingStateMachine.Start();
        samplingStateMachine.Start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //this goes into the StateMachine, "LandingStateMachine" and then goes through all the states it needs
        landingStateMachine.ProcessState();

        //Once the robot has landed it will start the sampling program and states
        if (landingStateMachine.IsDone()){
            samplingStateMachine.ProcessState();

        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
 }