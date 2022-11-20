package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.util.Reversible

@Config
object ArmConstants {
    @JvmField var ticksPerUnit = 131.0 / 90.0
    @JvmField var homePos = Reversible(0.0, 256.25)
    @JvmField var groundPos = Reversible(0.0, 0.0)
    @JvmField var lowPos = Reversible(0.0, 0.0)
    @JvmField var medPos = Reversible(0.0, 0.0)
    @JvmField var highPos = Reversible(0.0, 0.0)
    @JvmField var kP = 0.005
    @JvmField var kI = 0.0
    @JvmField var kD = 0.0
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0
    @JvmField var kCos = 0.3
    @JvmField var maxVel = 0.0
    @JvmField var maxAccel = 0.0
    @JvmField var allowedPositionError = 1.0
    @JvmField var testPower = 0.7
}