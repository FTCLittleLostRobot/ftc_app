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

    HardwareMecanumBase robot;
    private SamplingStateMachine samplingStateMachine = new SamplingStateMachine();
    MecanumMove moveRobot;
    ColorFinder colorFinder;


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
        this.colorFinder.init(hardwareMap);
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
    public void loop() {
        samplingStateMachine.ProcessState();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}