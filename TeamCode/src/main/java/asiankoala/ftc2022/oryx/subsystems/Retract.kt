package asiankoala.ftc2022.oryx.subsystems

import asiankoala.ftc2022.oryx.subsystems.constants.RetractConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Retract(
    private val servo: KServo
) : KSubsystem() {
    fun retract() {
        servo.position = RetractConstants.retract
    }
}