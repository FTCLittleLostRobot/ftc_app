package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Nicholas on 2017-10-25. TODO:Remember to change the Constants to suit this year's Robot, not MediumBot
 */
@Autonomous(name = "Blue Left Auto" , group = "Linear OpMode")

public class BlueLeftAuto extends LinearOpMode
{
    VuforiaLocalizer vuforia;
    HardwareLLR robot = new HardwareLLR();
    ModernRoboticsI2cGyro gyro = null;

    ElapsedTime vufoClock = new ElapsedTime();
    public void runOpMode()
    {
        //robot.teleInit(hardwareMap);
        robot.init(hardwareMap);
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        BotAutoMoveUtil botMove = new BotAutoMoveUtil(gyro , robot.right_frnt , robot.right_rear , robot.left_frnt , robot.left_rear);
        gyro.calibrate();
        gyro.resetZAxisIntegrator();
        botMove.changeMode();
        //Init code below is for Vuforia
        /*int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AZGvwWP/////AAAAGct4CbaXGkKgqClN26216W8Pkairb/CM3qmr+u3dnku8mS2xatHqTMoDijWS72cFo7HeaAatyPtfzmN3ASYlFW792pDXScmWyzwVYaELT/LyCC+j/Bpt7SRhXUC8iYslXzfYb+N8PqSPIrcnwlSjC4K49i15lErTIJU4byhsEXA1KaqQAUTRjTeRkOzb99uumiA8qPLbQN5r1DCOhFJnfcuaMwMmkiOM/OxpI3KSdactcXTQx5AGp5LsLMGgnEWY7TyY/CINfwLEI2xRcP2UUZD5kIX0yYXgNH8DF7+LlbS4aqPajHhNeruC3Q+v2c1oxPy25CahvxikokHN47Dxgi5nGiZIltw+kG2Q3B2ldKHS";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary*/
        telemetry.addData("Program Place" , "Ready to Run");
        telemetry.update();
        waitForStart();
        //relicTrackables.activate();
        vufoClock.reset();
        /*while (vufoClock.seconds() < 5.0)
        {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
            }
            else
                {
                telemetry.addData("VuMark", "not visible");
                }

            telemetry.update();
        }*/
        botMove.changeMode();
        robot.glyphElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.glyphElevator.setPower(1);
        sleep(2000);
        robot.glyphElevator.setPower(0);
        botMove.setDist(ConstUtil.offRampDist10_27);
//        botMove.motorPwrs(0.05);
        //botMove.turnGyro(4 ,0.2); //4 is correct. no need to worry
        botMove.setDist(ConstUtil.blueLeftDistL);
  //      botMove.motorPwrs(0.2);
        //botMove.turnGyro(-ConstUtil.blueLeftAngL , 0.3);
        telemetry.addData("Program Place" , "Done");
        telemetry.update();

        /*if (vuMark == "LEFT")
        {
            botMove.turnGyro(-72.36 , 0.2); //Negative numbers turn the robot right
            botMove.setDist(12.59);
            botMove.motorPowers(0.3);
            botMove.turnGyro(72.36 , 0.2); //Positive numbers turn the robot left
        }
        else if (vuMark == "CENTER")
        {
            botMove.turnGyro(-46.36 , 0.2);
            botMove.setDist(16.58);
            botMove.motorPowers(0.3);
            botMove.turnGyro(46.36 , 0.2);
        }
        else if (vuMark == "RIGHT")
        {
            botMove.turnGyro(-32 , 0.2);
            botMove.setDist(22.54);
            botMove.motorPowers(0.3);
            botMove.turnGyro(32.17 , 0.2);
        }

        */
    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
