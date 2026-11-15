package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.ArcadeDrive;

@Autonomous(name = "Drive Straight")
public class auton extends LinearOpMode {
    ArcadeDrive drive = new ArcadeDrive();
    ElapsedTime timer = new ElapsedTime();

    private static final double DRIVE_POWER = 0.3;   // tune this
    private static final double DRIVE_TIME = 0.25;    // seconds, tune this

    @Override
    public void runOpMode() {
        drive.init(hardwareMap);

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        timer.reset();
        while (opModeIsActive() && timer.seconds() < DRIVE_TIME) {
            drive.drive(DRIVE_POWER, 0);   // throttle, no spin
            telemetry.addData("Time", timer.seconds());
            telemetry.update();
        }

        drive.drive(0, 0);   // stop
    }
}