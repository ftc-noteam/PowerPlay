package asiankoala.ftc2022.oryx.subsystems.constants

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.math.NVector

@Config
object DriveConstants {
    @JvmField var kP = 0.0
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0

    // driving scalars
    @JvmField var xSS = 1.0
    @JvmField var ySS = 1.0
    @JvmField var rSS = 1.0

    @JvmField var xIS = 0.3
    @JvmField var yIS = 0.3
    @JvmField var rIS = 0.3

    @JvmField var xDS = 0.8
    @JvmField var yDS = 0.8
    @JvmField var rDS = 0.4

    val speedScalars = NVector(xSS, ySS, rSS)
    val intakeScalars = NVector(xIS, yIS, rIS)
    val depositScalars = NVector(xDS, yDS, rDS)
}