package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Basic TeleOp", group="Linear Opmode")
public class BasicTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        // Direction setup
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // --- DIAGNOSTIC MODE ---
            if (gamepad1.y) {
                frontLeftMotor.setPower(0.5);
            } else if (gamepad1.b) {
                frontRightMotor.setPower(0.5);
            } else if (gamepad1.x) {
                backLeftMotor.setPower(0.5);
            } else if (gamepad1.a) {
                backRightMotor.setPower(0.5);
            } else {
                // --- DRIVE MODE ---
                
                // y = Only Forward/Backward from Left Stick
                double y = -gamepad1.left_stick_y; 

                // x = Strafing moved to Bumpers (M1/M2)
                double x = 0;
                if (gamepad1.left_bumper) {
                    x = -1.0; // Strafe Left
                } else if (gamepad1.right_bumper) {
                    x = 1.0;  // Strafe Right
                }

                // rx = Turning on Right Stick
                double rx = gamepad1.right_stick_x;

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                frontLeftMotor.setPower(frontLeftPower);
                backLeftMotor.setPower(backLeftPower);
                frontRightMotor.setPower(frontRightPower);
                backRightMotor.setPower(backRightPower);
            }

            telemetry.addData("Controls", "LS: Fwd/Bwd, Bumpers: Strafe, RS: Turn");
            telemetry.update();
        }
    }
}
