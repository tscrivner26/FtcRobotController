package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(preselectTeleOp = "Beta_TeleOp")
public class AutoBlue extends LinearOpMode {
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

            //move forward into correct pixel area
            rightFront.setPower(-0.0);
            leftFront.setPower(0.0);
            rightRear.setPower(-0.0);
            leftRear.setPower(0.0);

            //turn left or right
            rightFront.setPower(-0.0);
            leftFront.setPower(0.0);
            rightRear.setPower(-0.0);
            leftRear.setPower(0.0);

            //secure pixel

            //turn towards backdrop

            //move forwards toward backdrop

            //drop pixel off

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

        }
    }
}

//This file is purely for testing Autonomous code
//The above code is the basis for meets 2 and 3 of the 2023-2024 Center stage season

//Notes:
//rF- lF+ rR- lR+ = forwards
//rF+ lF- rR+ lR- = backwards
//rF- lF- rR+ lR+ = strafe left
//rF+ lF+ rR- lR- = strafe right
//rF- lF+ rR- lR+ = turn left (unsure, fix if necessary)
//rF+ lF- rR+ lR- = turn right (unsure, fix if necessary)
//rF lF rR lR =

