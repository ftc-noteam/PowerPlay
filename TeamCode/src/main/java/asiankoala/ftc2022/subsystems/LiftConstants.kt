package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config

@Config
object LiftConstants {
    @JvmField var ticksPerUnit = 305.0 / 10.0
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0
    @JvmField var maxVel = 0.0
    @JvmField var maxAccel = 0.0
    @JvmField var testPower = 0.5


    @JvmField var kP = 0.5
    @JvmField var kI = 0.0
    @JvmField var kD = 0.005
    @JvmField var kG = 0.25
    @JvmField var home = 0.0
    @JvmField var med = 2.0
    @JvmField var high = 10.0
    @JvmField var disabledPosition = home
    @JvmField var allowedPositionError = 2.0
}