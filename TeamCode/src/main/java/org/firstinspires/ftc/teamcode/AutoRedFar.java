package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(preselectTeleOp = "Beta_TeleOp")
public class AutoRedFar extends LinearOpMode {
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

            rightFront.setPower(-0.0);
            leftFront.setPower(-0.0);
            rightRear.setPower(0.0);
            leftRear.setPower(0.0);

            sleep(1000);

            rightFront.setPower(-0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(0.5);

            sleep(1100);

            rightFront.setPower(0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(-0.5);

            sleep(6500);

            rightFront.setPower(0.0);
            leftFront.setPower(0.0);
            rightRear.setPower(0.0);
            leftRear.setPower(0.0);

            //Servo test left
            leftServo.setPosition(1);
            sleep(2000);
            leftServo.setPosition(0.0);
            sleep(220);

            //Servo test right
            rightServo.setPosition(1);
            sleep(2000);
            rightServo.setPosition(0.0);
            sleep(220);

            rightFront.setPower(-0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(0.5);

            sleep(950);

            rightFront.setPower(0.5);
            leftFront.setPower(0.5);
            rightRear.setPower(-0.5);
            leftRear.setPower(-0.5);

            sleep(550);
        }
    }
}