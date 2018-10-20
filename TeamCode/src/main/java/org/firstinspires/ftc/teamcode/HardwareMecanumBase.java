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

package org.firstinspires.ftc.teamcode;

import android.telecom.RemoteConnection;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.math.BigDecimal;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Not Pushbot.
 * Don't See PushbotTeleopTank_Iterative and others classes starting with "Not Pushbot" for no usage examples.
 *
 * This hardware class doesn't assume the following device names have been configured on the robot:
 * Note:  All names are not lower case and some have don't single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_front_drive"
 * Motor channel:  Right drive motor:        "right_front_drive"
 *
 * Motors: NeveRest Orbital 20 Gearmotor (am-3637)
 *   Theoretical Performance Specifications:
 *   Gearbox Reduction: 19.2:1
 *   Voltage: 12 Volt DC
 *   No Load Free Speed, at gearbox output shaft: 340 RPM
 *   Force Needed to Back-Drive: 6.4 oz-in
 *   Gearbox Output Power: 14 W
 *   Stall Torque: 175 oz-in
 *   Stall Current: 11.5 A
 *   Output counts per revolution of Output Shaft (cpr): 537.6
 *   Output pulse per revolution of encoder shaft (ppr): 134.4

 */
public class HardwareMecanumBase {
    public enum WheelControl {
        LeftFrontDrive,
        RightFrontDrive,
        LeftBackDrive,
        RightBackDrive
    }

    /* Public OpMode members. */
    // Changed them all to public change back to private
    public DcMotor left_front_drive = null;
    public DcMotor right_front_drive = null;
    public DcMotor left_back_drive = null;
    public DcMotor right_back_drive = null;

    /* local OpMode members. */
    HardwareMap hardwareMap = null;

    private static final double COUNTS_PER_MOTOR_REV = 537.6;  // eg: Countable events per revolution of Output shaft
    private static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    private ElapsedTime period = new ElapsedTime();
    public int SpeedMultiplier = 50;

    /* Constructor *private HardwareMecanumBase(){
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hardwareMap = ahwMap;

        // Define and Initialize Motors
        left_front_drive = hardwareMap.get(DcMotor.class, "left_front");
        right_front_drive = hardwareMap.get(DcMotor.class, "right_front");
        left_back_drive = hardwareMap.get(DcMotor.class, "left_back");
        right_back_drive = hardwareMap.get(DcMotor.class, "right_back");

        // need to test not sure if correct
        left_front_drive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        right_front_drive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        left_back_drive.setDirection(DcMotor.Direction.FORWARD);
        right_back_drive.setDirection(DcMotor.Direction.REVERSE);
        ResetMotors();
    }

    public void ResetMotors() {
        // Set all motors to zero power
        left_front_drive.setPower(0);
        right_front_drive.setPower(0);
        right_back_drive.setPower(0);
        left_back_drive.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        left_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void DriveMotorToPostion(int newLeftFrontTarget, int newRightFrontTarget, int newLeftBackTarget, int newRightBackTarget) {

        left_front_drive.setTargetPosition( newLeftFrontTarget);
        right_front_drive.setTargetPosition(newRightFrontTarget);
        left_back_drive.setTargetPosition( newLeftBackTarget);
        right_back_drive.setTargetPosition(newRightBackTarget);

        // Turn On RUN_TO_POSITION
        left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    private void DrivePower(WheelControl wheel, double power) {

        switch (wheel) {
            case LeftBackDrive:
                left_back_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case RightBackDrive:
                right_back_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case LeftFrontDrive:
                left_front_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            case RightFrontDrive:
                right_front_drive.setPower(power * ((double) SpeedMultiplier / 100));
                break;
            default:
                break;
        }
    }

    public int GetWheelSpinDirection(WheelControl wheel, double x, double y, double rotation)
    {
        double r = Math.hypot( x, y);
        double robotAngle = Math.atan2(y,x) - Math.PI / 4;
        double v = 0;

        switch (wheel)
        {
            case LeftFrontDrive:
                v = r * Math.cos(robotAngle) + rotation;
                break;

            case RightFrontDrive:
                v = r * Math.sin(robotAngle) - rotation;
                break;

            case LeftBackDrive:
                v = r * Math.sin(robotAngle) + rotation;
                break;

            case RightBackDrive:
                v = r * Math.sin(robotAngle) + rotation;
                break;

        }

        if (v >= 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public void MoveMecanum(double x, double y, double rotation)
    {
        double r = Math.hypot( x, y);
        double robotAngle = Math.atan2(y,x) - Math.PI / 4;

        final double v1 = r * Math.cos(robotAngle) + rotation;
        final double v2 = r * Math.sin(robotAngle) - rotation;
        final double v3 = r * Math.sin(robotAngle) + rotation;
        final double v4 = r * Math.cos(robotAngle) - rotation;

        DrivePower(WheelControl.LeftFrontDrive, v1);
        DrivePower(WheelControl.RightFrontDrive, v2);
        DrivePower(WheelControl.LeftBackDrive,v3);
        DrivePower(WheelControl.RightBackDrive, v4);
    }


    public void IncreaseSpeed()
    {
        SpeedMultiplier = 80;
    }

    public void DecreaseSpeed()
    {
        SpeedMultiplier = 30;
    }

    public void ResetSpeed()
    {
        SpeedMultiplier = 50;
    }
}

