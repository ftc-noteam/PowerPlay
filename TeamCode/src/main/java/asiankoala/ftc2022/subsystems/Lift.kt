package asiankoala.ftc2022.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Lift(
    val leadMotor: KMotor,
    private val secondMotor: KMotor,
    private val thirdMotor: KMotor
) : Subsystem() {
    companion object {
        val homePos: Double = TODO()
        val groundPos: Double = TODO()
        val lowPos: Double = TODO()
        val medPos: Double = TODO()
        val highPos: Double = TODO()
        val ticksPerUnit: Double = TODO()
    }

    fun setPos(pos: Double) {
        leadMotor.setProfileTarget(pos)
    }

    override fun periodic() {
        secondMotor.power = leadMotor.power
        thirdMotor.power = leadMotor.power
    }
}