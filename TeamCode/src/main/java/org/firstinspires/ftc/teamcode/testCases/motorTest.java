package org.firstinspires.ftc.teamcode.testCases;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Simple Motor OpMode", group = "Samples")
public class motorTest extends LinearOpMode {

    private DcMotor testMotor;

    @Override
    public void runOpMode() {

        testMotor = hardwareMap.get(DcMotor.class, "test_motor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.aWasReleased()){
                testMotor.setPower(0.5);
            } else if (gamepad1.bWasReleased()) {
                testMotor.setPower(-0.5);
            } else if (gamepad1.xWasReleased()) {
                testMotor.setPower(0);
            }
        }


        testMotor.setPower(0);
    }

}