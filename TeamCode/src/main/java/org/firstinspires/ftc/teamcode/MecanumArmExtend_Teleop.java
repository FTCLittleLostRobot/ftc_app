/*
   Little Lost Robots
   Core Devs: Danielle
*/


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.controllers.ArmExtend;

import org.firstinspires.ftc.teamcode.controllers.LanderNoEncoder;

/*
 *   This program should be used to make sure all wires are in and in the correct ports.
 *   If a wire is in the wrong port or not in at all this program will let you know!
 */

@TeleOp(name="Mecanum: Arm Extend", group="TestingMecanum")
@Disabled
public class MecanumArmExtend_Teleop extends OpMode {

    /* Declare OpMode members. */
    private HardwareMecanumBase robot = new HardwareMecanumBase(); // use the class created to define a Mencanums 's hardware
    private ArmExtend armExtend = new ArmExtend();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.init(hardwareMap);
        this.armExtend.init(robot, telemetry);



        telemetry.addData("Say", "Hello Driver");    //this shows the robot is ready

    }

    @Override
    public void loop () {

        if (gamepad2.dpad_up) {
            robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
            armExtend.ExtendingArm(100, 0.1);
        }
        else{
            robot.ArmExtend.setDirection(DcMotor.Direction.FORWARD);
            robot.ArmExtend.setPower(0);

        }

        if (gamepad2.dpad_down){
            robot.ArmExtend.setDirection(DcMotor.Direction.REVERSE);
            armExtend.ExtendingArm(100, 0.1);
        }

    }
}

