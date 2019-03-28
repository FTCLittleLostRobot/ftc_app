// Little Lost Robots
//Core Devs: Danielle and Caden

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TestingMecanum: lift", group="TestingMecanum")
@Disabled
public class MecanumLift_Teleop extends OpMode{

    HardwareMecanumBase robot       = new HardwareMecanumBase(); // use the class created to define a Mencanum's hardware



    @Override
    public void init() {

        robot.init(hardwareMap);


        // Turn On RUN_TO_POSITION
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }



    @Override
    public void start() {
    }


    @Override
    public void loop(){

        int newLiftTarget;
        boolean a = gamepad2.a;
        boolean b = gamepad2.b;

        if (a) {
            if (!robot.lift.isBusy()) {
                newLiftTarget = robot.lift.getCurrentPosition() + (int)(1 * HardwareTestingBase.COUNTS_PER_INCH);
                robot.lift.setTargetPosition(newLiftTarget);
                robot.lift.setPower(.2);
                telemetry.addData("State A", "Going Down");
                telemetry.update();

            }
        }


        if (b){
            if (!robot.lift.isBusy()) {
                newLiftTarget = robot.lift.getCurrentPosition() -  (int)(1 * HardwareTestingBase.COUNTS_PER_INCH) ;
                robot.lift.setTargetPosition(newLiftTarget);
                robot.lift.setPower(.2);
                telemetry.addData("State B", "Going Up");
                telemetry.update();

            }

        }



    }
}

