package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Pivot(private val servo: KServo) : Subsystem() {
    companion object {
        const val homePos = 0.0
        const val groundPos = 0.0
        const val lowPos = 0.0
        const val medPos = 0.0
        const val highPos = 0.0
    }

    fun setPosition(pos: Double) {
        servo.position = pos
    }
}