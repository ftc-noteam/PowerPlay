package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Arm(private val motor: KMotor) : Subsystem() {
    @Config
    companion object {
        @JvmField var ticksPerUnit = 0.0
        @JvmField var homePos = Reversible(0.0, 0.0)
        @JvmField var groundPos = Reversible(0.0, 0.0)
        @JvmField var lowPos = Reversible(0.0, 0.0)
        @JvmField var medPos = Reversible(0.0, 0.0)
        @JvmField var highPos = Reversible(0.0, 0.0)
        @JvmField var kP = 0.0
        @JvmField var kI = 0.0
        @JvmField var kD = 0.0
        @JvmField var kS = 0.0
        @JvmField var kV = 0.0
        @JvmField var kA = 0.0
        @JvmField var kCos = 0.0
        @JvmField var maxVel = 0.0
        @JvmField var maxAccel = 0.0
        @JvmField var allowedPositionError = 0.0
    }

    fun setPos(pos: Double) {
        motor.setProfileTarget(pos)
    }
}
