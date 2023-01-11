package asiankoala.ftc2022.oryx.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Pivot(private val servo: KServo) : KSubsystem() {
    fun setPos(pos: Double) {
        servo.position = pos
    }
}
