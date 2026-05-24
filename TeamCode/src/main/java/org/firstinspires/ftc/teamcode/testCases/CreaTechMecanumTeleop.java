package org.firstinspires.ftc.teamcode.testCases;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="CreaTechMecanumTeleop", group="Tutorial")
public class CreaTechMecanumTeleop extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Hardware mapping with the back motor swap fix
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        // Direction setup
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set Zero Power Behavior to BRAKE
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                // --- BRAKE MODE ---
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                telemetry.addData("Brakes", "ACTIVE");
            } else if (gamepad1.y) {
                // --- DIAGNOSTICS ---
                frontLeftMotor.setPower(0.5);
            } else if (gamepad1.b) {
                frontRightMotor.setPower(0.5);
            } else if (gamepad1.x) {
                backLeftMotor.setPower(0.5);
            } else {
                // --- DRIVE MODE ---
                
                // y = Forward/Backward from Left Stick
                double y = -gamepad1.left_stick_y; 

                // x = Strafing from Left Stick
                double x = gamepad1.left_stick_x * 1.1; // 1.1 multiplier to counteract friction

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

            telemetry.addData("Controls", "LS: Move, RS: Turn, A: BRAKE");
            telemetry.update();
        }
    }
}
