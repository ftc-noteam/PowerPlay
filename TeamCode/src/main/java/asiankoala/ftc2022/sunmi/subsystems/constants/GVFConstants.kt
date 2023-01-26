package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.profile.disp.Constraints
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign

@Config
object GVFConstants {
    @JvmField var kN = 0.6
    @JvmField var kTheta = 10.0
    @JvmField var kOmega = 1.0
    @JvmField var epsilon = 1.0
    @JvmField var thetaEpsilon = 2.0
    @JvmField var constraints = Constraints(0.0, 0.0)
    @JvmField var kS = 0.0
    @JvmField var kV = 0.0
    @JvmField var kA = 0.0
    @JvmField var kd = 0.9
    @JvmField var pd = 2.7
    @JvmField var bd = 1.4
    val errorMap = { x: Double ->
        val u = abs(x / bd).pow(pd)
        kd * u * sign(x) / (1.0 + u)
    }
    val errorMapDeriv = { x: Double ->
        val u = (1.0 + abs(x / bd)).pow(pd)
        ((pd / bd) * abs(x / bd).pow(pd - 1.0)) / (u * u)
    }
}