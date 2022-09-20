package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem

class Claw(
    private val leftServo: KServo,
    private val rightServo: KServo,
    private val distanceSensor: KDistanceSensor
) : Subsystem() {
    companion object {
        val gripPos: Pair<Double, Double> = TODO()
        val homePos: Pair<Double, Double> = TODO()
        val depositPos: Pair<Double, Double> = TODO()
        val distanceThreshold: Double = TODO()
    }

    val readyToGrab get() = distanceSensor.lastRead < distanceThreshold

    fun setPos(pos: Pair<Double, Double>) {
        leftServo.position = pos.first
        rightServo.position = pos.second
    }
}