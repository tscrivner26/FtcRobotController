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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Main extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        DcMotorEx hangOver = hardwareMap.get(DcMotorEx.class, "hangOver");
        DcMotor northTower = hardwareMap.get(DcMotor.class, "northTower"); //left
        DcMotor southTower = hardwareMap.get(DcMotor.class, "southTower"); //right
        Servo bracingServo = hardwareMap.get(Servo.class, "bracingServo");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

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

            if(gamepad2.y) {
                bracingServo.setPosition(0.6);
            } else if (gamepad2.x) {
                bracingServo.setPosition(-0.6);
            } else {
                bracingServo.setPosition(0.0);
            }

            if(gamepad2.dpad_up){
                hangOver.setPower(-0.95);
            } else if(gamepad2.dpad_down){
                hangOver.setPower(0.95);
            } else if(hangOver.getCurrentPosition() == -10){
                hangOver.setPower(0.0);
            } else if(hangOver.getCurrentPosition() == -1356){
                hangOver.setPower(0.0);
            } else {
                hangOver.setPower(0.0);
            }

            //height limit -1356
            // lowest limit -10

            if(gamepad2.left_bumper) {
                northTower.setPower(-1);
                southTower.setPower(1); //swap north and south values if needed
            } else if(gamepad2.right_bumper) {
                northTower.setPower(-1);
                southTower.setPower(1);
            } else if (gamepad2.dpad_left) {
                northTower.setPower(-1);
                southTower.setPower(1);
            } else if (gamepad2.dpad_right) {
                northTower.setPower(-1);
                southTower.setPower(1);
            } else {
                northTower.setPower(0.0);
                southTower.setPower(0.0);
            }
        }
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
        northTower.setPower(0);
        southTower.setPower(0);
        hangOver.setPower(0.0);

    }
}
//if(gamepad2.left_bumper) {
//        northTower.setPower(-0.96);
//        southTower.setPower(0.96); //swap north and south values if needed
//        } else if(gamepad2.right_bumper) {
//       northTower.setPower(-0.96);
//        southTower.setPower(0.96);
//        } else if (gamepad2.dpad_left) {
//        northTower.setPower(-0.96);
//        southTower.setPower(0.96);
//        } else if (gamepad2.dpad_right) {
//        northTower.setPower(-0.96);
//       southTower.setPower(0.96);
//        } else {
//        northTower.setPower(0.0);
//        southTower.setPower(0.0);
