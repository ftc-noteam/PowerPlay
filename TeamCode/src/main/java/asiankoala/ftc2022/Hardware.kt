package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.*
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.KEncoder
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
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
        .createEncoder(Lift.ticksPerUnit, false)
        .zero(Lift.homePos)
//        .withMotionProfileControl(
//            PIDGains(Lift.kP, Lift.kI, Lift.kD),
//            FFGains(Lift.kS, Lift.kV, Lift.kA, kG = Lift.kG),
//            MotionConstraints(Lift.maxVel, Lift.maxAccel),
//            allowedPositionError = Lift.allowedPositionError,
//            disabledPosition = Lift.disabledPosition
//        )
        .build()

//    val liftLeadMotor = MotorFactory("liftLead")
//        .float
//        .forward
//        .createEncoder(Lift.ticksPerUnit, false)
//        .zero(Lift.homePos)
//        .withMotionProfileControl(
//            PIDGains(Lift.kP, Lift.kI, Lift.kD),
//            FFGains(Lift.kS, Lift.kV, Lift.kA, kG = Lift.kG),
//            MotionConstraints(Lift.maxVel, Lift.maxAccel),
//            allowedPositionError = Lift.allowedPositionError,
//            disabledPosition = Lift.disabledPosition
//        )
//        .build()
//
    val liftBottom = MotorFactory("liftBottom")
        .float
        .build()

    val liftLeft = MotorFactory("liftLeft")
        .float
        .build()

//    val armMotor = MotorFactory("arm")
//        .float
//        .createEncoder(Arm.ticksPerUnit, false)
//        .zero(Arm.homePos[false])
//        .withMotionProfileControl(
//            PIDGains(Arm.kP, Arm.kI, Arm.kD),
//            FFGains(Arm.kS, Arm.kV, Arm.kA, kCos = Arm.kCos),
//            MotionConstraints(Arm.maxVel, Arm.maxAccel),
//            allowedPositionError = Arm.allowedPositionError
//        )
//        .build()

    val arm = MotorFactory("arm")
        .float
        .forward
        .createEncoder(Arm.ticksPerUnit, false)
        .build()

    val claw = KServo("claw")
        .startAt(ClawConstants.openPos)

//    val pivot = KServo("pivot")
//        .startAt(Constants.pivotHome)

//
//    val distanceSensor = KDistanceSensor("distanceSensor")
//
//    private val leftEncoder = KEncoder(fr, ticksPerUnit, true)
//        .reverse
//        .zero()
//    private val rightEncoder = KEncoder(fl, ticksPerUnit, true)
//        .zero()
//
//    private val auxEncoder = KEncoder(br, ticksPerUnit, true)
//        .reverse
//        .zero()

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
        @JvmField var TRACK_WIDTH = 0.0
        @JvmField var PERP_TRACKER = 0.0
    }
}
