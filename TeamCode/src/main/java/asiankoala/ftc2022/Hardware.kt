package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.*
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Hardware(startPose: Pose) {
    val fl = MotorFactory("fl")
        .brake
//        .forward
        .reverse
        .build()

    val bl = MotorFactory("bl")
        .brake
//        .forward
        .reverse
        .build()

    val br = MotorFactory("br")
        .brake
//        .reverse
        .build()

    val fr = MotorFactory("fr")
        .brake
//        .reverse
        .build()

    val liftLead = MotorFactory("liftLead")
        .float
        .forward
        .pairEncoder(br, EncoderFactory(LiftConstants.ticksPerUnit)
                .zero(LiftConstants.home)
        )
        .withPositionControl(
            PIDGains(LiftConstants.kP, LiftConstants.kI, LiftConstants.kD),
            FFGains(kG = LiftConstants.kG),
            allowedPositionError = LiftConstants.allowedPositionError,
            disabledPosition = LiftConstants.disabledPosition
        )
//        .withMotionProfileControl(
//            PIDGains(Lift.kP, Lift.kI, Lift.kD),
//            FFGains(Lift.kS, Lift.kV, Lift.kA, kG = Lift.kG),
//            MotionConstraints(Lift.maxVel, Lift.maxAccel),
//            allowedPositionError = Lift.allowedPositionError,
//            disabledPosition = Lift.disabledPosition
//        )
        .build()

    val liftBottom = MotorFactory("liftBottom")
        .reverse
        .float
        .build()

    val liftLeft = MotorFactory("liftLeft")
        .forward
        .float
        .build()

    val arm = MotorFactory("arm")
        .float
        .reverse
        .createEncoder(EncoderFactory(ArmConstants.ticksPerUnit)
                .reverse
                .zero(ArmConstants.home)
        )
        .withPositionControl(
            PIDGains(ArmConstants.kP, ArmConstants.kI, ArmConstants.kD),
            FFGains(kCos = ArmConstants.kCos),
            allowedPositionError = ArmConstants.allowedPositionError
        )
//        .withMotionProfileControl(
//            PIDGains(ArmConstants.kP, ArmConstants.kI, ArmConstants.kD),
//            FFGains(kCos = ArmConstants.kCos),
//            MotionConstraints(ArmConstants.maxVel, ArmConstants.maxAccel),
//            allowedPositionError = 0.5
//        )
        .build()

    val claw = KServo("claw")
        .startAt(ClawConstants.open)
//
    val pivot = KServo("pivot")
        .startAt(PivotConstants.home)

    val leftEncoder = EncoderFactory(ticksPerUnit)
        .reverse
        .revEncoder
        .build(bl)

    val rightEncoder = EncoderFactory(ticksPerUnit)
        .reverse
        .revEncoder
        .build(fr)

    val auxEncoder = EncoderFactory(ticksPerUnit)
        .reverse
        .revEncoder
        .build(fl)

    val odometry = KThreeWheelOdometry(
        leftEncoder,
        rightEncoder,
        auxEncoder,
        TRACK_WIDTH,
        PERP_TRACKER,
        startPose
    )

    @Config
    companion object {
        private const val ticksPerUnit = 1892.3724
        @JvmField var TRACK_WIDTH = 12.1259842
        @JvmField var PERP_TRACKER = 3.346
    }
}
