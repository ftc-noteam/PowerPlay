package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Arm(val motor: KMotor) : Subsystem() {
    companion object {
        val ticksPerUnit: Double = TODO()
        val homePos = Reversible<Double>(TODO(), TODO())
        val groundPos = Reversible<Double>(TODO(), TODO())
        val lowPos = Reversible<Double>(TODO(), TODO())
        val medPos = Reversible<Double>(TODO(), TODO())
        val highPos = Reversible<Double>(TODO(), TODO())
    }

    fun setPos(pos: Double) {
        motor.setProfileTarget(pos)
    }
}