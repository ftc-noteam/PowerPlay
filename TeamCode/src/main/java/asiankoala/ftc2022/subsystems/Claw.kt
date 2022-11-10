package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Claw(
    private val servo: KServo,
    private val distanceSensor: KDistanceSensor
) : Subsystem() {
    @Config
    companion object {
        @JvmField var gripPos = 0.0
        @JvmField var openPos = 0.0
        @JvmField var distanceThreshold = 0.0
    }

    val readyToGrab get() = distanceSensor.lastRead < distanceThreshold

    fun setPos(pos: Double) {
        servo.position = pos
    }
}