package asiankoala.ftc2022.oryx.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Claw(
    private val servo: KServo,
) : Subsystem() {
    fun setPos(pos: Double) {
        servo.position = pos
    }
}
