package asiankoala.ftc2022.oryx.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Lift(
    private val rt: KMotor,
    rb: KMotor,
    lt: KMotor,
    lb: KMotor
) : Subsystem() {
    private val chainedMotors = listOf(rb, lt, lb)

    val pos get() = rt.pos
    val vel get() = rt.vel

    fun setTarget(pos: Double) {
        rt.setProfileTarget(pos)
    }

    override fun periodic() {
        chainedMotors.forEach { it.power = rt.power }
    }
}