package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Pivot(private val servo: KServo) : Subsystem() {
    fun setPos(pos: Double) {
        servo.position = pos
    }
}