package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class ArcadeDrive extends LinearOpMode {
//init

    //drive motors
    private DcMotor MotorLF;
    private DcMotor MotorRB;
    private DcMotor MotorRF;
    private DcMotor MotorLB;
    private double leftpower;
    private double rightpower;

    private SmoothScaler leftScaler = new SmoothScaler(100, 50);
    private SmoothScaler rightScaler = new SmoothScaler(100, 50);
    private final double DEADZONE = 0.2;

    @Override
    public void runOpMode() {
        MotorLB = hardwareMap.get(DcMotor.class, "MotorLB");
        MotorLF = hardwareMap.get(DcMotor.class, "MotorLF");
        MotorRB = hardwareMap.get(DcMotor.class, "MotorRB");
        MotorRF = hardwareMap.get(DcMotor.class, "MotorRF");

        MotorRB.setPower(0);
        MotorLB.setPower(0);
        MotorRF.setPower(0);
        MotorLB.setPower(0);

        MotorLB.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorLF.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorRB.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorRF.setDirection(DcMotorSimple.Direction.REVERSE);

        MotorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        rightScaler.resetTime();
        leftScaler.resetTime();

        while (!isStopRequested()) {
            if (gamepad1.left_stick_y >= DEADZONE || gamepad1.left_stick_y <= -DEADZONE) {
                leftpower = gamepad1.left_stick_y;
                rightpower = gamepad1.left_stick_y;

            } else {
                leftpower = 0;
                rightpower = 0;
            }

            if (gamepad1.right_stick_x >= DEADZONE || gamepad1.right_stick_x <= -DEADZONE) {
                rightpower -= gamepad1.right_stick_x;
                leftpower += gamepad1.right_stick_x;

            }

            rightpower = rightScaler.smoothScaler(rightpower);
            leftpower = leftScaler.smoothScaler(leftpower);

            telemetry.addData("Left Power (No Saturate):", leftpower);
            telemetry.addData("Right Power (No Saturate):", rightpower);
            telemetry.update();

            MotorRB.setPower(Saturate (rightpower, -1 , 1));
            MotorLB.setPower(Saturate(leftpower, -1 , 1));
            MotorRF.setPower(Saturate (rightpower, -1 , 1));
            MotorLF.setPower(Saturate(leftpower, -1 , 1));

            telemetry.addData("MotorRF Power:", MotorRF.getPower());
            telemetry.addData("MotorRB Power:", MotorRB.getPower());
            telemetry.addData("MotorLF Power:", MotorLF.getPower());
            telemetry.addData("MotorLB Power:", MotorLB.getPower());


        }


    }

    private static double Saturate (double input , double min , double max) {
        if (input > max) {
            return max;
        }
        if (input < min) {
            return min;
        }
        return input;
    }
}