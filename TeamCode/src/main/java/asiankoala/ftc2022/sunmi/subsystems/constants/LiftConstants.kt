package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object LiftConstants {
    @JvmField var kP = 0.82
    @JvmField var kI = 0.0
    @JvmField var kD = 0.006
    @JvmField var kG = 0.1
    @JvmField var vel = 140.0
    @JvmField var accel = 140.0
    @JvmField var home = 0.0
    @JvmField var ground = 0.0
    @JvmField var juncMult = 10.0
    @JvmField var low = 7.7
    @JvmField var med = low + juncMult
    @JvmField var high = low + juncMult * 2.0
}