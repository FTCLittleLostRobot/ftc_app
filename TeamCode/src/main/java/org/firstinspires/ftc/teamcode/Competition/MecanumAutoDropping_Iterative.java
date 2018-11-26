/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.StateMachines.LandingStateMachine;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

@Autonomous(name="Mecanum: Dropping", group="Mecanum")
public class MecanumAutoDropping_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private LandingStateMachine landingStateMachine = new LandingStateMachine();
    private MecanumMove moveRobot;
    private LanderEncoder lander    = new LanderEncoder();


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

        robot.init(hardwareMap);
        this.moveRobot.init(robot);
        this.lander.init(robot, telemetry);
        landingStateMachine.init(telemetry, this.lander, this.moveRobot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }



    @Override
    public void start() {
        landingStateMachine.Start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        landingStateMachine.ProcessState();
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}