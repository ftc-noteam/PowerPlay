package asiankoala.ftc2022.miyuki

import asiankoala.ftc2022.miyuki.subsystems.Arm
import asiankoala.ftc2022.miyuki.subsystems.constants.ArmConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.ClawConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.LiftConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.PivotConstants
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.math.Pose

class Hardware(startPose: Pose, hasZeroPositionAlready: Boolean = false) {
    val fl = MotorFactory("fl")
        .brake
        .reverse
        .withStaticFeedforward(FuckThisRobot.flks)
        .build()

    val bl = MotorFactory("bl")
        .brake
        .reverse
        .withStaticFeedforward(FuckThisRobot.blks)
        .build()

    val br = MotorFactory("br")
        .brake
        .forward
        .withStaticFeedforward(FuckThisRobot.brks)
        .build()

    val fr = MotorFactory("fr")
        .brake
        .forward
        .withStaticFeedforward(FuckThisRobot.frks)
        .build()

    val liftLead = MotorFactory("liftLead")
        .float
        .forward
        .pairEncoder(br, EncoderFactory(LiftConstants.ticksPerUnit)
                .zero(if(hasZeroPositionAlready) LiftConstants.home else LiftConstants.home)
        )
        .withMotionProfileControl(
            PIDGains(LiftConstants.kP, LiftConstants.kI, LiftConstants.kD),
            FFGains(kG = LiftConstants.kG, kS = LiftConstants.kS, kV = LiftConstants.kV, kA = LiftConstants.kA),
            MotionConstraints(LiftConstants.maxVel, LiftConstants.maxAccel),
            allowedPositionError = LiftConstants.allowedPositionError,
            disabledPosition = LiftConstants.disabledPosition
        )
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
        .brake
        .reverse
        .createEncoder(EncoderFactory(ArmConstants.ticksPerUnit)
                .reverse
                .zero(if(hasZeroPositionAlready) Arm.lastPos else ArmConstants.home)
        )
        .withMotionProfileControl(
            PIDGains(ArmConstants.kP, ArmConstants.kI, ArmConstants.kD),
            FFGains(kCos = ArmConstants.kCos, kS = ArmConstants.kS, kV = ArmConstants.kV, kA = ArmConstants.kA),
            MotionConstraints(ArmConstants.maxVel, ArmConstants.maxAccel, ArmConstants.maxDeccel),
            allowedPositionError = ArmConstants.allowedPositionError
        )
        .build()

    val claw = KServo("claw")
        .startAt(ClawConstants.open)

    val pivot = KServo("pivot")
        .startAt(PivotConstants.home)

    val leftEncoder = EncoderFactory(ticksPerUnit)
        .reverse
        .revEncoder
        .build(fl)

    val rightEncoder = EncoderFactory(ticksPerUnit)
        .reverse
        .revEncoder
        .build(fr)

    val auxEncoder = EncoderFactory(ticksPerUnit)
        .revEncoder
        .build(bl)

    @Config
    companion object {
        private const val ticksPerUnit = 1892.3724
        @JvmField var TRACK_WIDTH = 12.1259842
        @JvmField var PERP_TRACKER = 3.346
    }
}
