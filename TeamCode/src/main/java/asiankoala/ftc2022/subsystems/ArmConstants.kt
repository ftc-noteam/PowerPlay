package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.util.Reversible

@Config
object ArmConstants {
    @JvmField var ticksPerUnit = 1.9666
    @JvmField var homePos = Reversible(0.0, 256.25)
    @JvmField var home = 264.0
    @JvmField var groundPos = Reversible(0.0, 0.0)
    @JvmField var lowPos = Reversible(0.0, 0.0)
    @JvmField var medPos = Reversible(0.0, 0.0)
    @JvmField var highPos = Reversible(0.0, 0.0)
    @JvmField var kP = 0.015
    @JvmField var kI = 0.0
    @JvmField var kD = 0.001
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0
    @JvmField var kCos = 0.22
    @JvmField var maxVel = 0.0
    @JvmField var maxAccel = 0.0
    @JvmField var allowedPositionError = 1.0
    @JvmField var testPower = 0.5
}