/* Little Lost Robots
   Core Devs: Danielle
*/

/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


// only works for 6 inches

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="TestingBase: Encoder Test", group="TestingBase")
@Disabled
public class TestingBaseEncoder_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareTestingBase robot = new HardwareTestingBase();
    private ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 0.1;
    static final double TURN_SPEED = 0.25;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
            
        robot.left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.left_drive.getCurrentPosition(),
                robot.right_drive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(FORWARD_SPEED,  12,  12, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   5, -5, 5.0 );  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(FORWARD_SPEED, -10, -10, 5.0);  // S3: Reverse 24 Inches with 4 Sec timeout
            
        telemetry.addData("Path", "Complete");
        telemetry.update();

    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.left_drive.getCurrentPosition() + (int)(leftInches * HardwareTestingBase.COUNTS_PER_INCH);
            newRightTarget = robot.right_drive.getCurrentPosition() + (int)(rightInches * HardwareTestingBase.COUNTS_PER_INCH);
            robot.left_drive.setTargetPosition(newLeftTarget);
            robot.right_drive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.left_drive.setPower(Math.abs(speed));
            robot.right_drive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.left_drive.isBusy() && robot.right_drive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.left_drive.getCurrentPosition(),
                        robot.right_drive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.left_drive.setPower(0);
            robot.right_drive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
