/* Little Lost Robots
   Core Devs: Danielle
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Sensor: Read Gyro", group = "Sensor")
@Disabled
public class SensorGyroRead extends OpMode
{
    public ModernRoboticsI2cGyro gyro = null;

    public void init()
    {
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
