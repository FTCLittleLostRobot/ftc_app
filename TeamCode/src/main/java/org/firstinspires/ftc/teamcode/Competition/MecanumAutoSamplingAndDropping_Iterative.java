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

    HardwareMecanumBase robot;
    private SamplingStateMachine samplingStateMachine = new SamplingStateMachine();
    MecanumMove moveRobot;
    ColorFinder colorFinder;
    private LandingStateMachine landingStateMachine = new LandingStateMachine();
    private LanderEncoder lander    = new LanderEncoder();



    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */

        this.lander.init(robot, telemetry);
        landingStateMachine.init(telemetry, this.lander, this.moveRobot);

        robot.init(hardwareMap);
        this.moveRobot.init(robot);
        this.colorFinder.init(hardwareMap);
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