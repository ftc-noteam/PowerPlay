package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Pivot(private val servo: KServo) : Subsystem() {
    companion object {
        val homePos = Reversible<Double>(TODO(), TODO())
        val groundPos = Reversible<Double>(TODO(), TODO())
        val lowPos = Reversible<Double>(TODO(), TODO())
        val medPos = Reversible<Double>(TODO(), TODO())
        val highPos = Reversible<Double>(TODO(), TODO())
    }

    fun setPos(pos: Double) {
        servo.position = pos
    }
}