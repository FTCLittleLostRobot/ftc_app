package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HardwareMecanumBase;
import org.firstinspires.ftc.teamcode.StateMachines.DabEmoteStateMachine;
import org.firstinspires.ftc.teamcode.StateMachines.LandingStateMachine;
import org.firstinspires.ftc.teamcode.controllers.LanderEncoder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="Mecanum: Dabbing", group="Mecanum")
public class MecanumAutoDabbing_Iterative extends OpMode {

    private HardwareMecanumBase robot;
    private LandingStateMachine landingStateMachine;
    private DabEmoteStateMachine dab;
    private MecanumMove moveRobot;
    private LanderEncoder lander;


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

}
