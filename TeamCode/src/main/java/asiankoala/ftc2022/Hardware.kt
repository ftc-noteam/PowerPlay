package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.testing.Hardware
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.DisabledPosition
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

    val liftLeadMotor = MotorFactory("liftLeadMotor")
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

    val liftFollowTop = MotorFactory("liftFollowTop")
        .float
        .build()

    val liftFollowBottom = MotorFactory("liftFollowBottom")
        .float
        .build()

    val armMotor = MotorFactory("arm")
        .float
        .createEncoder(Arm.ticksPerUnit, false)
        .zero(Arm.homePos[false])
        .withMotionProfileControl(
            PIDGains(TODO(), TODO(), TODO()),
            FFGains(kCos = TODO()),
            MotionConstraints(TODO(), TODO()),
            allowedPositionError = TODO(),
            disabledPosition = DisabledPosition(TODO())
        )
        .build()

    val clawLeftServo = KServo("clawLeft")
        .startAt(TODO())

    val clawRightServo = KServo("clawRight")
        .startAt(TODO())

    val pivotServo = KServo("pivot")

    val distanceSensor = KDistanceSensor("distanceSensor")

    private val leftEncoder = KEncoder(fr, ticksPerUnit, true)
        .reverse
        .zero()
    private val rightEncoder = KEncoder(fl, ticksPerUnit, true)
        .zero()

    private val auxEncoder = KEncoder(br, ticksPerUnit, true)
        .reverse
        .zero()

    val odometry = KThreeWheelOdometry(
        leftEncoder,
        rightEncoder,
        auxEncoder,
        TODO(),
        TODO(),
        startPose
    )

    companion object {
        private const val ticksPerUnit = 1892.3724
    }
}
