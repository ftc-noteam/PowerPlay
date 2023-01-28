package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object SimpleGVFConstants {
    @JvmField var kN = 0.6
    @JvmField var kOmega = 1.0
    @JvmField var kF = 6.0
    @JvmField var kS = 6.0
    @JvmField var epsilon = 1.0
    @JvmField var thetaEpsilon = 2.0
    val errorMap: (Double) -> Double = { it }
}