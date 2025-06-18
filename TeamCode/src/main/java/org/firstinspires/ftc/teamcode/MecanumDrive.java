package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(group = "TeleOps", name = "Mecanum Drive")
public class MecanumDrive extends OpMode
{
    private final double MAX_POWER = 0.5;

    private DcMotor motorLF, motorLB, motorRF, motorRB = null;

    private void MotorInit(DcMotor motor)
    {
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0);
    }

    @Override
    public void init()
    {
        // Init motors
        motorLF = hardwareMap.get(DcMotor.class, "motorLF");
        motorLB = hardwareMap.get(DcMotor.class, "motorLB");
        motorRF = hardwareMap.get(DcMotor.class, "motorRF");
        motorRB = hardwareMap.get(DcMotor.class, "motorRB");

        MotorInit(motorLF);
        MotorInit(motorLB);
        MotorInit(motorRF);
        MotorInit(motorRB);
    }

    @Override
    public void loop()
    {
        motorLF.setPower(Math.min(gamepad1.right_stick_y, MAX_POWER));
        motorLB.setPower(Math.min(gamepad1.right_stick_y, MAX_POWER));
        motorRF.setPower(Math.min(gamepad1.right_stick_y, MAX_POWER));
        motorRB.setPower(Math.min(gamepad1.right_stick_y, MAX_POWER));

    }
}
