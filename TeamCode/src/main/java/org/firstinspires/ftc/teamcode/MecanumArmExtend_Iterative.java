/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.controllers.ArmExtend;
import org.firstinspires.ftc.teamcode.StateMachines.SamplingStateMachine;
import org.firstinspires.ftc.teamcode.controllers.ColorFinder;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

@Autonomous(name="Mecanum: Arm Extend Autonomous", group="Mecanum")
@Disabled

public class MecanumArmExtend_Iterative extends OpMode {

    HardwareMecanumBase robot;
    private ArmExtend armExtend= new ArmExtend();



    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */

        robot = new HardwareMecanumBase();


        robot.init(hardwareMap);
        this.armExtend.init(robot, telemetry);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */


    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        armExtend.ExtendingArm(5000, 0.1);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}