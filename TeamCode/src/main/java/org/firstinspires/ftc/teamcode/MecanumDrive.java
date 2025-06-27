package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "TeleOps", name = "Mecanum Drive")
public class MecanumDrive extends OpMode
{
    private final double MAX_POWER = 0.5;

    private boolean firstLoop = true;
    public boolean buttonDU = true;
    public boolean buttonDD = true;
    public boolean leftTrigger = true;
    public boolean rightTrigger = true;

    private SmoothScaler ssHeading = new SmoothScaler(100, 50);
    private SmoothScaler ssForward = new SmoothScaler(100, 50);
    private SmoothScaler ssStrafe = new SmoothScaler(100, 50);
    public DcMotor motorLF, motorLB, motorRF, motorRB = null;
    public Servo leftClaw = null;
    public Servo rightClaw = null;
    public DcMotor slides = null;

    //this is a tele-op not a utility class so you cannot call it in another class
//    public MecanumDrive(HardwareMap hardwareMap) {
//    }

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

        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");

        slides = hardwareMap.dcMotor.get("slides");
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    //drive code
    @Override
    public void loop()
    {
        if(firstLoop){
            ssForward.elapsedTime.reset();
            ssStrafe.elapsedTime.reset();
            ssHeading.elapsedTime.reset();
            // bigger number moves left
            rightClaw.setPosition(.64);
            leftClaw.setPosition(.38);

            slides.setTargetPosition(300);
            slides.setPower(0.175);
            slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            firstLoop = false;
        }

        mecanumDrive( ssForward.smoothScaler(-gamepad1.left_stick_y), ssStrafe.smoothScaler(gamepad1.left_stick_x), ssHeading.smoothScaler(gamepad1.right_stick_x), MAX_POWER);

        //claw opening
        if(gamepad1.right_trigger > 0.1 && rightTrigger){

            rightClaw.setPosition(0.5);
            leftClaw.setPosition(0.5);

            rightTrigger = false;

        }

        if(gamepad1.right_trigger < 0.1 && !rightTrigger){

            rightTrigger = true;
        }



        //claw closing
        if(gamepad1.left_trigger > 0.1 && leftTrigger){

            rightClaw.setPosition(.64);
            leftClaw.setPosition(.4);

            leftTrigger = false;

        }

        if(gamepad1.left_trigger < 0.1 && !leftTrigger){

            leftTrigger = true;
        }




        if(gamepad1.dpad_down && buttonDD){

            slides.setTargetPosition(0);
            slides.setPower(.375);
            slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            buttonDD = false;

        }

        if(!gamepad1.dpad_down && !buttonDD){

            buttonDD = true;
        }


        if(gamepad1.dpad_up && buttonDU){

            slides.setTargetPosition(2400);
            slides.setPower(.375);
            slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            buttonDU = false;

        }

        if(!gamepad1.dpad_up && !buttonDU){

            buttonDU = true;
        }

    }

    public void mecanumDrive(double forward, double strafe, double heading, double speed){

        motorRF.setPower((((forward - strafe) * 1) - (heading * 1)) * speed);
        motorRB.setPower((((forward + strafe) * 1) - (heading * 1)) * speed);
        motorLB.setPower((((forward - strafe) * 1) + (heading * 1)) * speed);
        motorLF.setPower((((forward + strafe) * 1) + (heading * 1)) * speed);
    }


    public void resetDriveEncoders() {

        motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
