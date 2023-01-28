package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.Soyeon
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.GVFController
import com.asiankoala.koawalib.path.gvf.SimpleGVFController

class CommandPathGen(private val sunmi: Sunmi) {
    private fun genGVFController(
        path: HermitePath,
        kN: Double = SimpleGVFConstants.kN,
        kOmega: Double = SimpleGVFConstants.kOmega,
        kF: Double = SimpleGVFConstants.kF,
        kS: Double = SimpleGVFConstants.kS,
        epsilon: Double = SimpleGVFConstants.epsilon,
        thetaEpsilon: Double = SimpleGVFConstants.thetaEpsilon,
        errorMap: (Double) -> Double = SimpleGVFConstants.errorMap
    ) : GVFController = SimpleGVFController(
        path,
        sunmi.drive,
        kN, kOmega, kF, kS, epsilon, thetaEpsilon, errorMap
    )

    val firstDepositNoCmd = GVFCmd(sunmi.drive, genGVFController(initPath))
    val firstDepositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(initPath),
        ProjQuery(Soyeon(sunmi), 0.5)
    )

    val intakeNoCmd = GVFCmd(sunmi.drive, genGVFController(intakePath))
    val intakeWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(intakePath),
        ProjQuery(AutoIntakeSeq(sunmi), 1.0)
    )

    val depositNoCmd = GVFCmd(sunmi.drive, genGVFController(depositPath))
    val depositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(depositPath),
        ProjQuery(Soyeon(sunmi), 0.5)
    )
}