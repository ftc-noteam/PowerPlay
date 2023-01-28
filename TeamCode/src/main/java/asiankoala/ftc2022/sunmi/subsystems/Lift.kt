package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.subsystem.KSubsystem

class Lift(bl: KMotor) : KSubsystem() {
    private val lt = MotorFactory("lt")
        .reverse
        .pairEncoder(
            bl,
            EncoderFactory(367.0 / 12.0).reverse.zero()
        )
        .withMotionProfileControl(
            PIDGains(
                LiftConstants.kP,
                LiftConstants.kI,
                LiftConstants.kD
            ),
            FFGains(kG = LiftConstants.kG),
            MotionConstraints(LiftConstants.vel, LiftConstants.accel),
            0.1,
            0.0
        )
        .float
        .build()

    private val lb = MotorFactory("lb").float.build()
    private val rt = MotorFactory("rt").float.build()
    private val rb = MotorFactory("rb").float.reverse.build()
    private val limit = KLimitSwitch("limit")
    val chainedMotors = listOf(lb, rt, rb)
    var isAttemptingZero = false
    val pos get() = lt.pos
    val vel get() = lt.vel
    val setpoint get() = lt.setpoint

    fun setTarget(pos: Double) {
        lt.setProfileTarget(pos)
    }

    fun startAttemptingZero() {
        isAttemptingZero = true
    }

    override fun periodic() {
        chainedMotors.forEach { it.power = lt.power }
        if(isAttemptingZero && limit.invoke()) {
            lt.zero()
            isAttemptingZero = false
            setTarget(LiftConstants.home)
            Logger.logInfo("zeroed")
        }
    }
}