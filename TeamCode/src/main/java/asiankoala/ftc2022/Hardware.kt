package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Lift
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.DisabledPosition
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.KEncoder
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.odometry.KNewOdometry

class Hardware {
    val flMotor = MotorFactory("fl")
        .brake
        .forward
        .build()

    val blMotor = MotorFactory("bl")
        .brake
        .forward
        .build()

    val brMotor = MotorFactory("br")
        .brake
        .reverse
        .build()

    val frMotor = MotorFactory("fr")
        .brake
        .reverse
        .build()

    val liftLeadMotor = MotorFactory("liftLeft")
        .float
        .forward
        .createEncoder(Lift.ticksPerUnit, false)
        .zero(Lift.homePos)
        .withMotionProfileControl(
            PIDGains(TODO(), TODO(), TODO()),
            FFGains(kG = TODO(), kS = TODO(), kV = TODO(), kA = TODO()),
            MotionConstraints(TODO(), TODO()),
            allowedPositionError = TODO(),
            disabledPosition = DisabledPosition(TODO())
        )
        .build()

    val liftSecondMotor = MotorFactory("liftRight")
        .float
        .build()

    val liftThirdMotor = MotorFactory("liftMiddle")
        .float
        .build()

    val armMotor = MotorFactory("arm")
        .float
        .createEncoder(Arm.ticksPerUnit, false)
        .zero(Arm.homePos)
        .withMotionProfileControl(
            PIDGains(TODO(), TODO(), TODO()),
            FFGains(kCos = TODO()),
            MotionConstraints(TODO(), TODO()),
            allowedPositionError = 1.0,
            disabledPosition = DisabledPosition(TODO())
        )
        .build()

    val clawLeftServo = KServo("clawLeft")
        .startAt(TODO())

    val clawRightServo = KServo("clawRight")
        .startAt(TODO())

    val pivotServo = KServo("pivot")

    val distanceSensor = KDistanceSensor("distanceSensor")


    companion object {
        private const val ticksPerUnit = 1892.3724
    }

    val leftEncoder = KEncoder(flMotor, ticksPerUnit, true).zero()
    val rightEncoder = KEncoder(brMotor, ticksPerUnit, true).zero()
    val auxEncoder = KEncoder(frMotor, ticksPerUnit, true).zero()

}