/* Little Lost Robots
   Core Devs: Danielle,
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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Test: Motor Encoder RunTo", group="Mencanum")

public class SensorMotorEncoder_Test extends OpMode {

    DcMotor motorToTest = null;
    int newTarget = -1;
    int startingPos = -1;

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

        motorToTest = hardwareMap.get(DcMotor.class, "ArmDropRight");
        motorToTest.setDirection(DcMotor.Direction.FORWARD);
        startingPos = motorToTest.getCurrentPosition();

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
     */
    @Override
    public void loop() {
        telemetry.addData("Current State", state.toString());

        switch (state)
        {
            case Setup:
                encoderDrive_Start();
                state = RobotState.Start;
                break;

            case Start:
                if (encoderDrive_IsDone()) {
                    state = RobotState.Finish;
                }
                break;

            case Finish:
                encoderDrive_Complete();
                telemetry.addData("Robot", "Robot is done");
                state = RobotState.Done;
                break;
        }
        telemetry.addData("Starting Position", startingPos);
        telemetry.addData("Current Position", motorToTest.getCurrentPosition());
        telemetry.addData("New Target", newTarget);
    }

    public void encoderDrive_Start() {


        // Determine new target position, and pass to motor controller
        newTarget = motorToTest.getCurrentPosition() + 1000;

        // Turn On RUN_TO_POSITION
        motorToTest.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorToTest.setTargetPosition(newTarget);

        // reset the timeout time and start motion.
        motorToTest.setPower(.05);

        }

    public boolean encoderDrive_IsDone() {
        return !(motorToTest.isBusy()) ;
    }

    public void encoderDrive_Complete() {
        // Turn off RUN_TO_POSITION
        motorToTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Stop all motion;
        motorToTest.setPower(0);
    }
}