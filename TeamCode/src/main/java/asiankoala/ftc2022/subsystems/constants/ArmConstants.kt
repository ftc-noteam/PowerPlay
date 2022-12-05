package asiankoala.ftc2022.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ArmConstants {
    @JvmField var kS = 0.03
    @JvmField var kV = 0.00001
    @JvmField var kA = 0.0000
    @JvmField var maxVel = 400.0
    @JvmField var maxAccel = 700.0

    @JvmField var ticksPerUnit = 2.0434
    @JvmField var kP = 0.016
    @JvmField var kI = 0.003
    @JvmField var kD = 0.001
    @JvmField var kCos = 0.26
    @JvmField var allowedPositionError = 1.0


    @JvmField var home = -69.0 // thanks ritwik
    @JvmField var pickup = -66.0
    @JvmField var ground = 130.0
    @JvmField var low = 130.0
    @JvmField var med = 130.0
    @JvmField var high = 130.0
}