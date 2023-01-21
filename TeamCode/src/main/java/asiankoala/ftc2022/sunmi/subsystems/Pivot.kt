package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Pivot : Subsystem() {
    private val servo = KServo("pivot").startAt(PivotConstants.home)
    fun setPos(pos: Double) {
        servo.position = pos
    }
}
