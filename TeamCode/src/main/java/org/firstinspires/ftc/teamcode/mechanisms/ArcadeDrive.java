package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArcadeDrive {

    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private DcMotor leftFlyWheel, rightFlywheel, intake, surgical;


    public void init(HardwareMap hwMap){
        frontLeftMotor = hwMap.get(DcMotor.class, "frontLeftWheel");
        backLeftMotor = hwMap.get(DcMotor.class, "backLeftWheel");
        frontRightMotor = hwMap.get(DcMotor.class, "rightFrontWheel");
        backRightMotor = hwMap.get(DcMotor.class, "backRightWheel");

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftFlyWheel = hwMap.get(DcMotor.class, "leftFlywheel");
        rightFlywheel = hwMap.get(DcMotor.class, "rightFlywheel");
        intake = hwMap.get(DcMotor.class, "intake");
        surgical = hwMap.get(DcMotor.class, "surgical");

        leftFlyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        surgical.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        leftFlyWheel.setDirection(DcMotor.Direction.FORWARD);
        rightFlywheel.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.REVERSE);
        surgical.setDirection(DcMotor.Direction.REVERSE);

    }

    public void drive (double throttle, double spin) {
        double leftPower = throttle + spin;
        double rightPower = throttle - spin;
        double largest = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (largest > 1.0) {
            leftPower /= largest*0.5;
            rightPower /= largest*.5;
        }

        frontLeftMotor.setPower(leftPower);
        frontRightMotor.setPower(rightPower);
        backLeftMotor.setPower(leftPower);
        backRightMotor.setPower(rightPower);
    }
    public void takeIn(boolean on){
        intake.setDirection(DcMotor.Direction.REVERSE);
        intake.setPower(on ? 1.0 : 0.0);
    }

    public void spinFlywheels(boolean on){
        double power = on ? 1.0 : 0.0;
        leftFlyWheel.setPower(power);
        rightFlywheel.setPower(power);
    }

    public void runSurgical(boolean on){
        surgical.setPower(on ? 1.0 : 0.0);
    }

    public void moveOut(boolean on){
        intake.setDirection(DcMotor.Direction.FORWARD);
    }


}
