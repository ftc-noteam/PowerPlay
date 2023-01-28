package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object DriveConstants {
    @JvmField var blKStatic = 0.05
    @JvmField var brKStatic = 0.09
    @JvmField var yLimiter = 5.0
    @JvmField var xLimiter = 5.0
    @JvmField var rLimiter = 5.0
}