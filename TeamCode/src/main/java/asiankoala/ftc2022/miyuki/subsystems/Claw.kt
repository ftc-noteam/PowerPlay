package asiankoala.ftc2022.miyuki.subsystems

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Claw(
    private val servo: KServo,
//    private val distanceSensor: KDistanceSensor
) : Subsystem() {
    // distanceSensor.lastRead < Constants.clawDistanceThreshold
//    val readyToGrab get() = true

    fun setPos(pos: Double) {
        servo.position = pos
    }
}
