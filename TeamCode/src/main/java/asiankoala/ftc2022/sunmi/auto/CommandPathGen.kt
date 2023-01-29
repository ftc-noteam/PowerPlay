package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.Soyeon
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.subsystems.BasedGVFController
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.GVFController

class CommandPathGen(private val sunmi: Sunmi) {
    private fun genGVFController(
        path: HermitePath,
        kN: Double = SimpleGVFConstants.kN,
        kOmega: Double = SimpleGVFConstants.kOmega,
        kF: Double = SimpleGVFConstants.kF,
        kS: Double = SimpleGVFConstants.kS,
        epsilon: Double = SimpleGVFConstants.epsilon,
        thetaEpsilon: Double = SimpleGVFConstants.thetaEpsilon,
        errorMap: (Double) -> Double = SimpleGVFConstants.errorMap,
        epsilonToPID: Double = SimpleGVFConstants.epsilonToPID
    ) : GVFController = BasedGVFController(
        path,
        sunmi.drive,
        kN, kOmega, kF, kS, epsilon, thetaEpsilon, errorMap, epsilonToPID
    )

    val firstDepositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(initPath),
        ProjQuery(Soyeon(sunmi), 0.4)
    )

    fun intakeWithCmd(height: Double) = GVFCmd(
        sunmi.drive,
        genGVFController(
        intakePath,
        kF = 4.0,
        kOmega = 20.0,
        epsilon = 2.0
        ),
        ProjQuery(LiftCmd(sunmi.lift, height), 0.2),
    )

    val depositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(
            depositPath,
            kF = 6.0,
            kOmega = 25.0,
        ),
        ProjQuery(Soyeon(sunmi), 0.2)
    )

    val lowDeposit = GVFCmd(
        sunmi.drive,
        genGVFController(
            lowDepositPath,
            kF = 5.0,
            kOmega = 25.0,
        ),
    )

    val lowIntake = GVFCmd(
        sunmi.drive,
        genGVFController(
            lowIntakePath,
            kF = 4.0,
            kOmega = 25.0,
            epsilon = 2.0,
            thetaEpsilon = 2.0
        ),
    )
}