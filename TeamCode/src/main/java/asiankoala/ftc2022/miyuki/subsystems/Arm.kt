package asiankoala.ftc2022.miyuki.subsystems

import asiankoala.ftc2022.miyuki.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Arm(val motor: KMotor) : Subsystem() {
    fun setPos(pos: Double) {
        motor.setProfileTarget(pos)
    }

    val pos get() = motor.pos
    val vel get() = motor.vel

    companion object {
        var lastPos = ArmConstants.home
    }
}
