package asiankoala.ftc2022.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object LiftConstants {
    @JvmField var ticksPerUnit = 305.0 / 10.0
    @JvmField var kS = 0.05
    @JvmField var kV = 0.007
    @JvmField var kA = 0.0
    @JvmField var maxVel = 200.0
    @JvmField var maxAccel = 200.0
    @JvmField var kP = 0.5
    @JvmField var kI = 0.0
    @JvmField var kD = 0.005
    @JvmField var kG = 0.25
    @JvmField var home = 0.0
    @JvmField var ready = 2.0
    @JvmField var ground = ready
    @JvmField var low = ready
    @JvmField var med = ready
    @JvmField var high = 12.0
    @JvmField var disabledPosition = home
    @JvmField var allowedPositionError = 2.0
}