package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="trinityOutreachBot")
@Disabled

public class trinityOutreachBot extends LinearOpMode
{
    private final double MAX_POWER = 0.5;
    public boolean buttonY = true;
    public boolean buttonX = true;
    public boolean buttonA = true;
    public boolean buttonB = true;
    public boolean buttonDR = true;
    public boolean buttonDL = true;
    public boolean buttonDU = true;
    public boolean buttonDD = true;

    public double speed = .5;

    private SmoothScaler ssHeading = new SmoothScaler(100, 50);
    private SmoothScaler ssForward = new SmoothScaler(100, 50);
    private SmoothScaler ssStrafe = new SmoothScaler(100, 50);

    public Servo leftClaw = null;
    public Servo rightClaw = null;
    public DcMotor slides = null;


    @Override
    public void runOpMode() throws InterruptedException
    {


        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");

        slides = hardwareMap.dcMotor.get("slides");
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while(!isStarted() && !isStopRequested()){

            // bigger number moves left
            rightClaw.setPosition(.64);
            leftClaw.setPosition(.38);

            slides.setTargetPosition(300);
            slides.setPower(0.175);
            slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

        while (opModeIsActive())
        {

            //claw opening
            if(gamepad1.y && buttonY){

                rightClaw.setPosition(0.5);
                leftClaw.setPosition(0.5);

                buttonY = false;

            }

            if(!gamepad1.y && !buttonY){

                buttonY = true;
            }



            //claw closing
            if(gamepad1.b && buttonB){

                rightClaw.setPosition(.64);
                leftClaw.setPosition(.4);

                buttonB = false;

            }

            if(!gamepad1.b && !buttonB){

                buttonB = true;
            }




            if(gamepad1.dpad_down && buttonDD){

                slides.setTargetPosition(0);
                slides.setPower(.2);
                slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                buttonDD = false;

            }

            if(!gamepad1.dpad_down && !buttonDD){

                buttonDD = true;
            }


            if(gamepad1.dpad_up && buttonDU){

                slides.setTargetPosition(2400);
                slides.setPower(.2);
                slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                buttonDU = false;

            }

            if(!gamepad1.dpad_up && !buttonDU){

                buttonDU = true;
            }


        }
    }
}
