package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(preselectTeleOp = "Beta_TeleOp")
public class AutoBlueClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
        Servo rightServo = hardwareMap.get(Servo.class, "rightServo");

        waitForStart();
        if (opModeIsActive()) {

            //move forward
            rightFront.setPower(-0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(0.5);

            sleep(1100);

            //move back to give the pixel space
            rightFront.setPower(0.5);
            leftFront.setPower(-0.5);
            rightRear.setPower(0.5);
            leftRear.setPower(-0.5);

            sleep(350);

            //strafe toward backboard
            rightFront.setPower(-0.5);
            leftFront.setPower(-0.5);
            rightRear.setPower(0.5);
            leftRear.setPower(0.5);

            sleep(2100);

            //move forward to center the robot
            rightFront.setPower(-0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(0.5);

            sleep(350);

            //stop
            rightFront.setPower(-0.0);
            leftFront.setPower(-0.0);
            rightRear.setPower(0.0);
            leftRear.setPower(0.0);

            //Servo
            leftServo.setPosition(1);
            sleep(2000);
            leftServo.setPosition(0.0);
            sleep(220);

            //Servo
            rightServo.setPosition(1);
            sleep(2000);
            rightServo.setPosition(0.0);
            sleep(220);

            //back up after placing the pixels
            rightFront.setPower(0.5);
            leftFront.setPower(-0.5);
            rightRear.setPower(0.5);
            leftRear.setPower(-0.5);

            sleep(500);
        }
    }
}