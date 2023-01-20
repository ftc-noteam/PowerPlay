package asiankoala.ftc2022.oryx.subsystems

import asiankoala.ftc2022.oryx.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Arm : KSubsystem() {
    val arm1 = KServo("arm1")
        .startAt(ArmConstants.init)

    val arm2 = KServo("arm2")
        .startAt(ArmConstants.init)
        .reverse()

    fun setTarget(pos: Double) {
        arm1.position = pos
        arm2.position = pos
    }
}