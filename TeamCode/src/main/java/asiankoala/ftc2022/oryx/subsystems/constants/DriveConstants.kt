package asiankoala.ftc2022.oryx.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object DriveConstants {
    @JvmField var kP = 0.0
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0

    // driving scalars
    @JvmField var xSS = 1.0
    @JvmField var ySS = 1.0
    @JvmField var rSS = 0.8

    @JvmField var xIS = 0.3
    @JvmField var yIS = 0.3
    @JvmField var rIS = 0.3

    val speedScalars = listOf(xSS, ySS, rSS)
    val slowScalars = listOf(xIS, yIS, rIS)
}