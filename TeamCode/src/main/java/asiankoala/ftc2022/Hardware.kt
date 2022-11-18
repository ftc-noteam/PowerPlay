package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.*
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KEncoder
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Hardware(startPose: Pose) {
    val fl = MotorFactory("fl")
        .brake
        .forward
        .build()

    val bl = MotorFactory("bl")
        .brake
        .forward
        .build()

    val br = MotorFactory("br")
        .brake
        .reverse
        .build()

    val fr = MotorFactory("fr")
        .brake
        .reverse
        .build()

    val liftLead = MotorFactory("liftLead")
        .float
        .forward
        .pairEncoder(br, 1.0)
        .zero(Lift.homePos)
//        .withMotionProfileControl(
//            PIDGains(Lift.kP, Lift.kI, Lift.kD),
//            FFGains(Lift.kS, Lift.kV, Lift.kA, kG = Lift.kG),
//            MotionConstraints(Lift.maxVel, Lift.maxAccel),
//            allowedPositionError = Lift.allowedPositionError,
//            disabledPosition = Lift.disabledPosition
//        )
        .build()

    val liftBottom = MotorFactory("liftBottom")
        .float
        .zero()
        .build()

    val liftLeft = MotorFactory("liftLeft")
        .float
        .zero()
        .build()

    val arm = MotorFactory("arm")
        .float
//        .createEncoder(Arm.ticksPerUnit, false)
//        .zero(0.0)
//        .withMotionProfileControl(
//        .withPositionControl(
//            PIDGains(Arm.kP, Arm.kI, Arm.kD),
//            FFGains(),
//            allowedPositionError = 1.0
//            FFGains(Arm.kS, Arm.kV, Arm.kA, kCos = Arm.kCos),
//            MotionConstraints(Arm.maxVel, Arm.maxAccel),
//            allowedPositionError = Arm.allowedPositionError
//        )
        .createEncoder(Arm.ticksPerUnit)
        .build()

    val claw = KServo("claw")
        .startAt(ClawConstants.openPos)

//    val pivot = KServo("pivot")
//        .startAt(Constants.pivotHome)

//
//    val distanceSensor = KDistanceSensor("distanceSensor")
//

    val leftEncoder = KEncoder(bl, ticksPerUnit, true)
        .reverse
        .zero()

    val rightEncoder = KEncoder(fr, ticksPerUnit, true)
        .zero()

    val auxEncoder = KEncoder(fl, ticksPerUnit, true)
        .reverse
        .zero()

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
        @JvmField var TRACK_WIDTH = 12.0
        @JvmField var PERP_TRACKER = 4.0
    }
}
