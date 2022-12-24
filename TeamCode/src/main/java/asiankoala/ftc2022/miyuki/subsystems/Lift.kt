package asiankoala.ftc2022.miyuki.subsystems

import asiankoala.ftc2022.miyuki.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Lift(
    private val leadMotor: KMotor,
    private val bottomMotor: KMotor,
    private val leftMotor: KMotor
) : Subsystem() {
    fun setPos(pos: Double) {
        leadMotor.setProfileTarget(pos)
//        leadMotor.setPositionTarget(pos)
    }

    override fun periodic() {
        bottomMotor.power = leadMotor.power
        leftMotor.power = leadMotor.power
    }

    val pos get() = leadMotor.pos
    val vel get() = leadMotor.vel

    companion object {
        var lastPos = LiftConstants.home
    }
}
