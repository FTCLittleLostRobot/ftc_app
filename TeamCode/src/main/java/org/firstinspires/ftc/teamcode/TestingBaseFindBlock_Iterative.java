/* Little Lost Robots
   Core Devs: Caden, Nathan
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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous(name="TestingBase: Find Block Iterative Test", group="TestingBase")
@Disabled
public class TestingBaseFindBlock_Iterative extends OpMode {

    /* Declare OpMode members. */
    HardwareTestingBase robot = new HardwareTestingBase(); // use the class created to define a Pushbot's hardware

    static final double FORWARD_SPEED = 0.1;
    static final double TURN_SPEED = 0.25;


    enum RobotState
    {
        Setup,
        Start,
        Finish,
        Done
    }
    RobotState state = RobotState.Setup;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        int newLeftTarget;
        int newRightTarget;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     * init
        play [program]
        loop (until robot stops) {
        if (ColorSenseOut =< a : 29 | r : 89 | g : 56 | b : 2a) {
        [make robot stop];
        } else {
        [keep moving]
            }
        }
        [turn robot so front faces cube]
        [move forward]
        *end*
        Nathan Test Push
     */


    @Override
    public void loop() {
        telemetry.addData("Current State", state.toString());

        switch (state)
        {
            case Setup:
                encoderDrive_Start(FORWARD_SPEED, 24, 24);  // S1: Forward 47 Inches with 5 Sec timeout
                state = RobotState.Start;
                break;

            case Start:
/*                NormalizedRGBA colors = robot.colorSensor.getNormalizedColors();

                telemetry.addLine()
                        .addData("a", "%.3f", colors.alpha)
                        .addData("r", "%.3f", colors.red)
                        .addData("g", "%.3f", colors.green)
                        .addData("b", "%.3f", colors.blue);

                telemetry.update();

                if ((encoderDrive_IsDone()  ) || (colors.red >= 0.008 && colors.green >= 0.004)) {
                    //29 | r : 89 | g : 56 | b : 2a)) {fv starting now: colors.alpha >= 27 && colors.alpha < 31
                    state = RobotState.Finish;


                }
*/


                break;

            case Finish:
                encoderDrive_Complete();
                state = RobotState.Done;
                break;

            case Done:
                telemetry.addLine ()
                    .addData("Finished", "Complete");
                telemetry.update();

                break;
        }
    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public void encoderDrive_Start(double speed,
                                   double leftInches, double rightInches) {

        int newLeftTarget;
        int newRightTarget;

        // Determine new target position, and pass to motor controller
        newLeftTarget = robot.left_drive.getCurrentPosition() + (int) (leftInches * HardwareTestingBase.COUNTS_PER_INCH);
        newRightTarget = robot.right_drive.getCurrentPosition() + (int) (rightInches * HardwareTestingBase.COUNTS_PER_INCH);
        robot.left_drive.setTargetPosition(newLeftTarget);
        robot.right_drive.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        robot.left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        robot.left_drive.setPower(Math.abs(speed));
        robot.right_drive.setPower(Math.abs(speed));
    }

    public boolean encoderDrive_IsDone() {
        return !(robot.left_drive.isBusy() && robot.right_drive.isBusy());
    }

    public void encoderDrive_Complete() {
        // Stop all motion;
        robot.left_drive.setPower(0);
        robot.right_drive.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

