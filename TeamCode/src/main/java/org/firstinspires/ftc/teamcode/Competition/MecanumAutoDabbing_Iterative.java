/* Little Lost Robots
   Core Devs: Caden
*/

package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.StateMachines.DabEmoteStateMachine;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;
import org.firstinspires.ftc.teamcode.StateMachines.SamplingStateMachine;

@Autonomous(name="Mecanum:Dabbing", group="Mecanum")
public class MecanumAutoDabbing_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private DabEmoteStateMachine DabEmoteStateMachine;
    private MecanumMove moveRobot;

    @Override
    public void init() {
        /* Step 1: Setup of variables  */
        this.robot = new HardwareMecanumBase();
        this.moveRobot = new MecanumMove();
        this.DabEmoteStateMachine = new DabEmoteStateMachine();

        /* Step 2: Setup of hardware  */
        robot.init(hardwareMap);

        /* Step 3: Setup of controllers  */
        this.moveRobot.init(robot);

        /* Step 4: Setup of state machines  */
        // DELETE THIS: BUT REMEMBER TO FIX THIS CODE - this.DabEmoteStateMachine.init(telemetry, moveRobot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    @Override
    public void start() {
        DabEmoteStateMachine.Start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        //this goes into the StateMachine, "DabEmoteStateMachine" and then goes through all the states it needs
        DabEmoteStateMachine.ProcessState();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}