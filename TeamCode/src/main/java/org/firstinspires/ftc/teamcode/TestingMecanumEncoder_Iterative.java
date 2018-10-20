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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.controllers.MecanumMove;

@Autonomous(name="Mecanum: Encoder Iterative Test", group="Mecanum")
public class TestingMecanumEncoder_Iterative extends OpMode {

    /* Declare OpMode members. */
    HardwareMecanumBase robot;
    MecanumMove moveRobot;

    static final double FORWARD_SPEED = 0.1;
    static final double TURN_SPEED = 0.25;
    int testingLimitCounter = 0;

    enum RobotState
    {
        Setup,
        Drop,
        WaitForDrop,
        StrafingLeft,
        WaitForStrafeLeftt,
        CheckForGold,
        WaitForScoot,
        PushBloock,
        Done,

    }
    RobotState state;


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
        robot = new HardwareMecanumBase(); // use the class created to define a Pushbot's hardware
        moveRobot = new MecanumMove();

        robot.init(hardwareMap);
        this.moveRobot.init(robot);

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
        state = RobotState.Drop;
        testingLimitCounter = 0;
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Current State", state.toString());
        telemetry.addData("Say", "Hello Danielle v2");

        switch (state)
        {
            case Drop:
                // Start motor to drop
                state = RobotState.WaitForDrop;
                break;

            case WaitForDrop:
                state = RobotState.StrafingLeft;
                break;

            case StrafingLeft:
                this.moveRobot.Start(30, 6,1,0,0 );
                state = RobotState.WaitForStrafeLeftt;
                break;

            case WaitForStrafeLeftt:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.CheckForGold;
                }
                break;

            case CheckForGold:
                // Check returns 0
                int foundColumn;
                if (testingLimitCounter == 0) {
                    foundColumn = 0;
                    testingLimitCounter++;
                }
                else if (testingLimitCounter == 1)
                {
                    foundColumn = 4;
                    testingLimitCounter++;
                }
                else if (testingLimitCounter == 2) {
                    foundColumn = 2;
                    testingLimitCounter++;
                }
                else if (testingLimitCounter == 3) {
                    foundColumn = 3;
                    state = RobotState.Done;
                    break;
                }
                else {
                    foundColumn = 999;
                    testingLimitCounter++;
                    break;
                }
///////////////////////////////////////////////////////////////////////////////////////////////////////////// todo cut the top part once you add the color sections
                if (foundColumn == 0 || foundColumn == 1)
                {//
                    this.moveRobot.Start(30, 3,1,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 3 || foundColumn == 4)
                {
                    this.moveRobot.Start(30, 3,-1,0,0 );
                    state = RobotState.WaitForScoot;
                }
                else if (foundColumn == 2)
                {
                    this.moveRobot.Start(30, 24,0,-1,0 );
                    state = RobotState.PushBloock;
                }
                else if (foundColumn == -1)
                {
                    // if not found
                    this.moveRobot.Start(30, 6,1,0,0 );
                    state = RobotState.WaitForScoot;
                }
                break;

            case WaitForScoot:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.CheckForGold;
                }
                break;

            case PushBloock:
                if (this.moveRobot.IsDone()) {
                    this.moveRobot.Complete();
                    state = RobotState.Done;
                }
                break;

            case Done:
                state = RobotState.Done;
                break;


        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}