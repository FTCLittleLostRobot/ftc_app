package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

@Autonomous(name="Concept: Change PID", group = "Concept")
public class ConceptPID extends LinearOpMode {

        // our DC motor.
        private HardwareMecanumBase robot;

        DcMotorEx motorExLeft;


        public static final double NEW_P = 3.0;
        public static final double NEW_I = 0.05;
        public static final double NEW_D = 0.0;
        int startValue = 0;



        public void runOpMode() {
            // get reference to DC motor.
            // since we are using the Expansion Hub,
            // cast this motor to a DcMotorEx object.
            motorExLeft = (DcMotorEx)hardwareMap.get(DcMotor.class, "ArmDropLeft");

            startValue = motorExLeft.getCurrentPosition();



            motorExLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorExLeft.setTargetPosition(-120 + startValue );
            int encoderRangeValue = motorExLeft.getCurrentPosition();
            motorExLeft.setPower(0.8);

            // wait for start command.
            waitForStart();

            // get the PID coefficients for the RUN_USING_ENCODER  modes.
           PIDCoefficients pidOrig = motorExLeft.getPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION);

            // change coefficients using methods included with DcMotorEx class.
            PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);
            motorExLeft.setPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidNew);

            // re-read coefficients and verify change.
            PIDCoefficients pidModified = motorExLeft.getPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION);


            motorExLeft.setTargetPosition(-440 + startValue );
            motorExLeft.setPower(0.1);

            // display info to user.
            while(opModeIsActive()) {
                telemetry.addData("Runtime", "%.03f", getRuntime());
                telemetry.addData("P,I,D (orig)", "%.04f, %.04f, %.0f",
                        pidOrig.p, pidOrig.i, pidOrig.d);
                telemetry.addData("P,I,D (modified)", "%.04f, %.04f, %.04f",
                        pidModified.p, pidModified.i, pidModified.d);
                telemetry.update();


            }
        }
    }

