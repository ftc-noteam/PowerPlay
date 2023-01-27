package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Claw : KSubsystem() {
    private var isReading = false
    private val servo = KServo("claw").startAt(ClawConstants.close)
    private val sensor = KDistanceSensor("distance", 100.0)
    val lastRead get() = sensor.lastRead

    fun startReading() {
        isReading = true
    }

    fun stopReading() {
        isReading = false
    }

    fun setTarget(pos: Double) {
        servo.position = pos
    }

    override fun periodic() {
        if(isReading) {
            sensor.periodic()
        }
    }
}
