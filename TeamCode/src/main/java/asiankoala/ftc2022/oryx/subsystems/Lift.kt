package asiankoala.ftc2022.oryx.subsystems

import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.subsystem.KSubsystem

class Lift : KSubsystem() {
    private val lt = MotorFactory("lt").float.build()
    private val lb =MotorFactory("lb").float.reverse.build()
    private val rt = MotorFactory("rt").float.reverse.build()
    private val rb = MotorFactory("rb").float.build()
    private val chainedMotors = listOf(lb, rt, rb)

    val pos get() = lt.pos
    val vel get() = lt.vel

    fun setTarget(pos: Double) {
        lt.setPositionTarget(pos)
    }

    override fun periodic() {
        chainedMotors.forEach { it.power = lt.power }
    }
}