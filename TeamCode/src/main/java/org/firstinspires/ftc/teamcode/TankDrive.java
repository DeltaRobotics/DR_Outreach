package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp

public class TankDrive extends LinearOpMode {
//init

    //drive motors
    private DcMotor MotorLF;
    private DcMotor MotorRB;
    private DcMotor MotorRF;
    private DcMotor MotorLB;



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
                while (gamepad1.left_stick_y >= 0.65) {
                    MotorRB.setPower(0.6);
                    MotorLB.setPower(0.6);
                    MotorRF.setPower(0.6);
                    MotorLB.setPower(0.6);
                }
                while (gamepad1.left_stick_y <= -0.65) {
                    MotorRB.setPower(-0.6);
                    MotorLB.setPower(-0.6);
                    MotorRF.setPower(-0.6);
                    MotorLB.setPower(-0.6);

                }
                while (gamepad1.left_stick_x >= 0.65 ) {
                    MotorRB.setPower(-0.6);
                    MotorLB.setPower(-0.6);
                    MotorRF.setPower(0.6);
                    MotorLB.setPower(0.6);


                }
                while (gamepad1.left_stick_x <= -0.65) {
                    MotorRB.setPower(0.6);
                    MotorLB.setPower(0.6);
                    MotorRF.setPower(-0.6);
                    MotorLB.setPower(-0.6);

                }
            }
    }

}
