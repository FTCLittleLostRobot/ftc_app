package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nicholas on 2017-07-16.
 */


@TeleOp(name="Telemetry", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
@Disabled
public class Telemetry extends OpMode
{
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    /*
     * Code to run ONCE when the driver hits INIT
     */

    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Run Time",runtime);
        telemetry.addData("Hello" , "World!");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        // leftMotor  = hardwareMap.dcMotor.get("left_drive");
        // rightMotor = hardwareMap.dcMotor.get("right_drive");

        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //  rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        // telemetry.addData("Status", "Initialized");
    }



    public void start()
    {
        runtime.reset();
        telemetry.addData("Status", "Go Time!");
        telemetry.addData("Hello" , "World!");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("Hello" , "World!");

        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    public void stop()
    {
        telemetry.addData("Status", "All Done. Goodbye.");
        telemetry.addData("Hello" , "World!");
    }


}
