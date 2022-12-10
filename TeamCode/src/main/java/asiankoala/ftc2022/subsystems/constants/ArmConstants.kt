package asiankoala.ftc2022.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ArmConstants {
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0001
    @JvmField var kA = 0.0
    @JvmField var maxVel = 400.0
    @JvmField var maxAccel = 600.0
    @JvmField var maxDeccel = 500.0

    @JvmField var ticksPerUnit = 2.025157 // 4.1320754
    @JvmField var kP = 0.075
    @JvmField var kI = 0.000
    @JvmField var kD = 0.001
    @JvmField var kCos = 0.25
    @JvmField var allowedPositionError = 1.0

    @JvmField var home = -68.0 // thanks ritwik
    @JvmField var pickup = -69.0
    @JvmField var ground = 150.0
    @JvmField var low = 180.0
    @JvmField var med = 138.0
    @JvmField var high = 130.0
    @JvmField var autoInit = 242.0
}