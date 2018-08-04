package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Sensor: Read Gyro", group = "Sensor")
@Disabled
public class SensorGyroMove extends OpMode
{
    public ModernRoboticsI2cGyro gyro = null;
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    public void init()
    {
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "right");
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        telemetry.addData("->" , "Ready");
        telemetry.update();
    }

    public void loop()
    {
        telemetry.addData("Gyro Z Reading" , gyro.getIntegratedZValue());
        telemetry.update();
    }

}