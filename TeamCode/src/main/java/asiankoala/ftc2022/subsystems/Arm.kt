package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Arm(val motor: KMotor) : Subsystem() {
    companion object {
        val homePos: Double = TODO()
        val groundPos: Double = TODO()
        val lowPos: Double = TODO()
        val medPos: Double = TODO()
        val highPos: Double = TODO()
        val ticksPerUnit: Double = TODO()
    }

    fun setPos(pos: Double) {
        motor.setProfileTarget(pos)
    }
}