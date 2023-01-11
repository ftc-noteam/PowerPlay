package asiankoala.ftc2022.oryx

import asiankoala.ftc2022.oryx.subsystems.constants.*
import asiankoala.ftc2022.oryx.utils.AxonServoEncoder
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.servo.KServo

class Hardware {
    val fl = MotorFactory("fl")
        .brake
        .reverse
        .build()

    val bl = MotorFactory("bl")
        .brake
        .reverse
        .build()

    val br = MotorFactory("br")
        .brake
        .forward
        .build()

    val fr = MotorFactory("fr")
        .brake
        .forward
        .build()

    val lrt = MotorFactory("lrt")
        .float
        .forward
        .pairEncoder(br, EncoderFactory(LiftConstants.ticksPerUnit)
            .zero(LiftConstants.home)
        )
        .withMotionProfileControl(
            PIDGains(LiftConstants.kP, LiftConstants.kI, LiftConstants.kD),
            FFGains(kG = LiftConstants.kG, kS = LiftConstants.kS, kV = LiftConstants.kV, kA = LiftConstants.kA),
            MotionConstraints(LiftConstants.maxVel, LiftConstants.maxAccel),
            allowedPositionError = LiftConstants.allowedPositionError,
            disabledPosition = LiftConstants.disabledPosition
        )
        .build()

    val lrb = MotorFactory("lrb")
        .float
        .reverse
        .build()

    val llt = MotorFactory("llt")
        .float
        .forward
        .build()

    val llb = MotorFactory("llb")
        .float
        .reverse
        .build()

    val armL = KServo("armL")
            .startAt(ArmConstants.init)

    val armR = KServo("armL")
            .startAt(ArmConstants.init)
            .reverse()

    val axonEnc = AxonServoEncoder("axonEnc")
        .zero(ArmConstants.init)

    val claw = KServo("claw")
        .startAt(ClawConstants.open)

    val pivot = KServo("pivot")
        .startAt(PivotConstants.home)

    val retract = KServo("retract")
        .startAt(RetractConstants.extend)

    val leftEncoder = EncoderFactory(OdoConstants.ticksPerUnit)
        .reverse
        .revEncoder
        .build(fl)

    val rightEncoder = EncoderFactory(OdoConstants.ticksPerUnit)
        .reverse
        .revEncoder
        .build(fr)

    val auxEncoder = EncoderFactory(OdoConstants.ticksPerUnit)
        .revEncoder
        .build(bl)
}