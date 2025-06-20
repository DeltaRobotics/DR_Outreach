package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "TeleOps", name = "Mecanum Drive")
public class MecanumDrive extends OpMode
{
    private final double MAX_POWER = 0.5;

    private boolean firstLoop = true;

    private SmoothScaler ssHeading = new SmoothScaler(100, 50);
    private SmoothScaler ssForward = new SmoothScaler(100, 50);
    private SmoothScaler ssStrafe = new SmoothScaler(100, 50);
    private DcMotor motorLF, motorLB, motorRF, motorRB = null;

    private void MotorInit(DcMotor motor)
    {
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

        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);

        MotorInit(motorLF);
        MotorInit(motorLB);
        MotorInit(motorRF);
        MotorInit(motorRB);



    }

    @Override
    public void loop()
    {
        if(firstLoop){
            ssForward.elapsedTime.reset();
            ssStrafe.elapsedTime.reset();
            ssHeading.elapsedTime.reset();
            firstLoop = false;
        }

        mecanumDrive( ssForward.smoothScaler(-gamepad1.right_stick_y), ssStrafe.smoothScaler(gamepad1.right_stick_x), ssHeading.smoothScaler(gamepad1.left_stick_x), MAX_POWER);

    }

    public void mecanumDrive(double forward, double strafe, double heading, double speed){

        motorRF.setPower((((forward - strafe) * 1) - (heading * 1)) * speed);
        motorRB.setPower((((forward + strafe) * 1) - (heading * 1)) * speed);
        motorLB.setPower((((forward - strafe) * 1) + (heading * 1)) * speed);
        motorLF.setPower((((forward + strafe) * 1) + (heading * 1)) * speed);
    }


}
