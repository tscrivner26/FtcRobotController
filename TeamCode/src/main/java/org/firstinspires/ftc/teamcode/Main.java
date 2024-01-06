/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Main extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotorEx rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        DcMotorEx rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        DcMotorEx leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        DcMotorEx leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
       // DcMotorEx hangOver = hardwareMap.get(DcMotorEx.class, "hangOver");
       // DcMotorEx armLiftMotor = hardwareMap.get(DcMotorEx.class, "armLiftMotor");
       // DcMotorEx armPivotMotor = hardwareMap.get(DcMotorEx.class, "armPivotMotor");
       // DcMotor planeLauncher = hardwareMap.get(DcMotor.class, "planeLauncher");
        DcMotor pixelintakeMotor = hardwareMap.get(DcMotor.class, "pixelintakeMotor");
        // Servo planeBracingServo = hardwareMap.get(Servo.class, "planeBracingServo");
        Servo pixelreleaseServo = hardwareMap.get(Servo.class, "pixelreleaseServo");
        // Servo clawServo = hardwareMap.get(Servo.class, "clawServo");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        armPivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armPivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armLiftMotor.setDirection(DcMotor.Direction.REVERSE);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry

            //Speed Control
            double speed;
            if (gamepad1.right_bumper) {
                speed = 1;
            } else if (gamepad1.left_bumper) {
                speed = 0.3;
            } else {
                speed = 0.7;
            }

            double vertical = gamepad1.left_stick_y * speed;
            double horizontal = gamepad1.left_stick_x * speed;
            double pivot = gamepad1.right_stick_x * speed;



            rightFront.setPower(pivot + horizontal + vertical);
            rightRear.setPower(pivot - horizontal + vertical);
            leftFront.setPower(pivot + horizontal - vertical);
            leftRear.setPower(pivot - horizontal - vertical);

            //height limit -1356
            // lowest limit -10

            if(gamepad1.dpad_up) {
                hangOver.setPower(-0.8);
            } else if (gamepad1.dpad_down && hangOver.getCurrentPosition() != 30) {
                hangOver.setPower(1);
            } else {
                hangOver.setPower(0);
            }

            double lift = 0;

            if (gamepad2.left_bumper) {
                if (gamepad2.a) {
                    armPivotMotor.setTargetPosition(0);
                    armPivotMotor.setPower(1);
                    armPivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                } else if (gamepad2.y && gamepad2.left_bumper) {
                    armPivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                } else if (armPivotMotor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
                    armPivotMotor.setPower(0.0);
                }

                if (gamepad2.dpad_up) {
                    lift = lift -300;
                    armLiftMotor.setTargetPosition((int) (lift));
                    armLiftMotor.setPower(1);
                    armLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    armLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                } else if (gamepad2.dpad_down) {
                    armLiftMotor.setTargetPosition(0);
                    armLiftMotor.setPower(1);
                    armLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    armLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                } else if (gamepad2.y && gamepad2.left_bumper) {
                    armLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                } else if (armLiftMotor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
                    armLiftMotor.setPower(0.0);
                }

            } else {
                if (gamepad2.dpad_left) {
                    armPivotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    armPivotMotor.setPower(-1);
                } else if (gamepad2.dpad_right) {
                    armPivotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    armPivotMotor.setPower(1);
                } else if (armPivotMotor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
                    armPivotMotor.setPower(0.0);
                }
            }

            if (Math.abs(gamepad2.left_stick_y) > 0.05) {
                armLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                double arm = 0.65;
                arm = gamepad2.left_stick_y * arm;
                armLiftMotor.setPower(arm);
            } else if (armLiftMotor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
                armLiftMotor.setPower(0.0);
            }

            //if (gamepad1.dpad_left) {
            //    planeLauncher.setPower(-0.75);
           // } else {
           //     planeLauncher.setPower(0);
           // }
            if (gamepad1.dpad_right) {
                pixelintakeMotor.setPower(1);
            } else {
                pixelintakeMotor.setPower(0);
            }
            if (gamepad1.dpad_left) {
                pixelintakeMotor.setPower(-1);
            } else {
                pixelintakeMotor.setPower(0);
            }
            if (gamepad2.x) {
                pixelreleaseServo.setPosition(0.3); //hit to close the pixel
            } else if (gamepad2.b) {
                pixelreleaseServo.setPosition(-0.3); //hit to open the pixel
            }

            //if (gamepad1.x) {
            //    planeBracingServo.setPosition(0.7);
            //} else {
            //    planeBracingServo.setPosition(0.0);
            //}
            if(gamepad2.right_bumper){
                armLiftMotor.setTargetPosition(1500);
                armLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armLiftMotor.setPower(0.5);

            }
            if(armLiftMotor.getCurrentPosition() > 1200) {
                armPivotMotor.setTargetPosition(1200);
                armPivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armPivotMotor.setPower(0.5);
            }
            if(armLiftMotor.getCurrentPosition() < 1200) {
                armPivotMotor.setTargetPosition(0);
                armPivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armPivotMotor.setPower(0.5);
            }
            if(gamepad2.left_bumper){
                armLiftMotor.setTargetPosition(0);
                armLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armLiftMotor.setPower(0.5);
            }

            telemetry.addData("armPivot Current Position", armPivotMotor.getCurrentPosition());
            telemetry.addData("armPivot Target Position", armPivotMotor.getTargetPosition());
            telemetry.addData("armPivot Mode", armPivotMotor.getMode());
            telemetry.addData("armLift Current Position", armLiftMotor.getCurrentPosition());
            telemetry.addData("armLift Target Position", armLiftMotor.getTargetPosition());
            telemetry.addData("armLift Mode", armLiftMotor.getMode());
            telemetry.addData("hangOver Position", hangOver.getCurrentPosition());
            telemetry.update();
        }

        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
        armLiftMotor.setPower(0);
        armLiftMotor.setPower(0);
        hangOver.setPower(0);
        pixelintakeMotor.setPower(0);
       // planelauncer.setPower(0);

    }
}

//second webcam serial #: E4EC62A0

//free inputs: gamepad1.a, gamepad1.b, gamepad2.dpad_left, gamepad2.dpad_right

//previous code for running the arm to a specific encoder position, 888
//else if (gamepad2.b) {
//        armPivotMotor.setTargetPosition(888);
//        armPivotMotor.setPower(1);
//        armPivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);