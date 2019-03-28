/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;
import org.firstinspires.ftc.teamcode.StateMachines.SamplingStateMachine;

@Autonomous(name="Mecanum:Sampling", group="Mecanum")
public class MecanumAutoSampling_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private SamplingStateMachine samplingStateMachine;
    private MecanumMove moveRobot;
    private ColorFinder colorFinder;

    @Override
    public void init() {
        /* Step 1: Setup of variables  */
        this.robot = new HardwareMecanumBase();
        this.moveRobot = new MecanumMove();
        this.colorFinder = new ColorFinder();
        this.samplingStateMachine = new SamplingStateMachine();

        /* Step 2: Setup of hardware  */
        robot.init(hardwareMap);

        /* Step 3: Setup of controllers  */
        this.moveRobot.init(robot);
        this.colorFinder.init(hardwareMap);

        /* Step 4: Setup of state machines  */
        this.samplingStateMachine.init(telemetry, colorFinder, moveRobot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    @Override
    public void start() {
        samplingStateMachine.Start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        //this goes into the StateMachine, "SamplingStateMachine" and then goes through all the states it needs
        samplingStateMachine.ProcessState();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}