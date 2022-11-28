package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.*
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Hardware(startPose: Pose) {
//    val fl = MotorFactory("fl")
//        .brake
//        .forward
//        .build()
//
//    val bl = MotorFactory("bl")
//        .brake
//        .forward
//        .build()
//
    val br = MotorFactory("br")
        .brake
        .reverse
        .build()
//
//    val fr = MotorFactory("fr")
//        .brake
//        .reverse
//        .build()

    val liftLead = MotorFactory("liftLead")
        .float
        .forward
        .reverse
        .pairEncoder(br, EncoderFactory(1.0)
                .reverse
                .zero(LiftConstants.homePos)
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

//    val arm = MotorFactory("arm")
//        .float
////        .createEncoder(Arm.ticksPerUnit, false)
////        .zero(0.0)
////        .withMotionProfileControl(
////        .withPositionControl(
////            PIDGains(Arm.kP, Arm.kI, Arm.kD),
////            FFGains(),
////            allowedPositionError = 1.0
////            FFGains(Arm.kS, Arm.kV, Arm.kA, kCos = Arm.kCos),
////            MotionConstraints(Arm.maxVel, Arm.maxAccel),
////            allowedPositionError = Arm.allowedPositionError
////        )
//        .createEncoder(EncoderFactory(1.0)
//                .reverse
//                .zero(ArmConstants.homePos[true])
//        )
////        .withPositionControl(
////            PIDGains(),
////            FFGains(kCos = ArmConstants.kCos),
////            allowedPositionError = ArmConstants.allowedPositionError
////        )
//        .build()

//    val claw = KServo("claw")
//        .startAt(ClawConstants.gripPos)
//
//    val pivot = KServo("pivot")
//        .startAt(PivotConstants.pivotHome)
//
////
////    val distanceSensor = KDistanceSensor("distanceSensor")
////
//
//    val leftEncoder = EncoderFactory(ticksPerUnit)
//        .reverse
//        .revEncoder
//        .build(bl)
//
//    val rightEncoder = EncoderFactory(ticksPerUnit)
//        .revEncoder
//        .build(fr)
//
//    val auxEncoder = EncoderFactory(ticksPerUnit)
//        .reverse
//        .revEncoder
//        .build(fl)
//
//    val odometry = KThreeWheelOdometry(
//        leftEncoder,
//        rightEncoder,
//        auxEncoder,
//        TRACK_WIDTH,
//        PERP_TRACKER,
//        startPose
//    )

    @Config
    companion object {
        private const val ticksPerUnit = 1892.3724
        @JvmField var TRACK_WIDTH = 12.0
        @JvmField var PERP_TRACKER = 4.0
    }
}
