package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign

@Config
object SimpleGVFConstants {
    @JvmField var kN = 0.5
    @JvmField var kOmega = 30.0
    @JvmField var kF = 10.0
    @JvmField var kS = 0.5
    @JvmField var epsilon = 3.0
    @JvmField var thetaEpsilon = 5.0
    @JvmField var kP = 0.09
    @JvmField var epsilonToPID = 6.0
    @JvmField var kD = 0.0015
    val errorMap: (Double) -> Double = { it }
}