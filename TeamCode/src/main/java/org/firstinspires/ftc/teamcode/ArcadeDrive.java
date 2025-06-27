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
    private final double MAXDRIVEPOWER = 0.5;
    private final double MAXTURNOWER = 1 - MAXDRIVEPOWER;
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
        waitForStart();
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

            MotorRB.setPower(Saturate (rightpower, -1 , 1));
            MotorLB.setPower(Saturate(leftpower, -1 , 1));
            MotorRF.setPower(Saturate (rightpower, -1 , 1));
            MotorLB.setPower(Saturate(leftpower, -1 , 1));


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