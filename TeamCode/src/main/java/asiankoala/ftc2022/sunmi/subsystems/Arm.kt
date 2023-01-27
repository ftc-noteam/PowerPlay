package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Arm : KSubsystem() {
    private val arm1 = KServo("arm1")
        .startAt(ArmConstants.home)

    private val arm2 = KServo("arm2")
        .startAt(ArmConstants.home)
        .reverse()

    fun setTarget(pos: Double) {
        arm1.position = pos
        arm2.position = pos
    }
}