package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Arm(private val motor: KMotor) : Subsystem() {
    fun setPos(pos: Double) {
//        motor.setProfileTarget(pos)
        motor.setPositionTarget(pos)
    }

    fun openLoop(power: Double) {
        motor.power = power
    }

    val pos get() = motor.pos
    val vel get() = motor.vel
}
