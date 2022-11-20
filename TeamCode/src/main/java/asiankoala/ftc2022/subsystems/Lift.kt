package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem

class Lift(
    private val leadMotor: KMotor,
    private val secondMotor: KMotor,
    private val thirdMotor: KMotor
) : Subsystem() {
    fun setPos(pos: Double) {
        leadMotor.setProfileTarget(pos)
    }

    override fun periodic() {
        secondMotor.power = leadMotor.power
        thirdMotor.power = leadMotor.power
    }
}
