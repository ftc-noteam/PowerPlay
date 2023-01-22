package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object LiftConstants {
    @JvmField var kP = 0.5
    @JvmField var kI = 0.06
    @JvmField var kD = 0.005
    @JvmField var kG = 0.1
    @JvmField var vel = 100.0
    @JvmField var accel = 100.0
    @JvmField var home = 0.0
    @JvmField var ground = 0.0
    @JvmField var low = 8.0
    @JvmField var med = 18.0
    @JvmField var high = 28.0
    @JvmField var coneDelta = 4.0
    @JvmField var homeAfterIntaking = -0.5
}