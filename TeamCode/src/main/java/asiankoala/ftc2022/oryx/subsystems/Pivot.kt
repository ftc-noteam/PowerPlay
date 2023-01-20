package asiankoala.ftc2022.oryx.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Pivot : KSubsystem() {
    private val servo = KServo("pivot").startAt(0.5)
    fun setPos(pos: Double) {
        servo.position = pos
    }
}
