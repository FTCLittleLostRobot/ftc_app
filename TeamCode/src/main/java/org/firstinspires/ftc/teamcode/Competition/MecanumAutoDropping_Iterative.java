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
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="Mecanum: Dropping", group="Mecanum")
public class MecanumAutoDropping_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private LandingStateMachine landingStateMachine;
    private MecanumMove moveRobot;
    private LanderEncoder lander;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Step 1: Setup of variables  */
        robot = new HardwareMecanumBase();
        moveRobot = new MecanumMove();
        landingStateMachine = new LandingStateMachine();
        lander = new LanderEncoder();

        /* Step 2: Setup of hardware  */
        robot.init(hardwareMap);

        /* Step 3: Setup of controllers  */
        this.moveRobot.init(robot);
        this.lander.init(robot, telemetry);

        /* Step 4: Setup of state machines  */
        landingStateMachine.init(telemetry, this.lander, this.moveRobot, robot);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
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
        //this goes into the StateMachine, "LandingStateMachine" and then goes through all the states it needs

        landingStateMachine.ProcessState();
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}