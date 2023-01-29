package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.Soyeon
import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.subsystems.BasedGVFController
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.GVFController

class CommandPathGen(private val sunmi: Sunmi, private val autoPaths: AutoPaths) {
    private fun genGVFController(
        path: HermitePath,
        kN: Double = SimpleGVFConstants.kN,
        kOmega: Double = SimpleGVFConstants.kOmega,
        kF: Double = SimpleGVFConstants.kF,
        kS: Double = SimpleGVFConstants.kS,
        epsilon: Double = SimpleGVFConstants.epsilon,
        thetaEpsilon: Double = SimpleGVFConstants.thetaEpsilon,
        errorMap: (Double) -> Double = SimpleGVFConstants.errorMap,
        epsilonToPID: Double = SimpleGVFConstants.epsilonToPID,
        pidControlEnabled: Boolean = false
    ) : GVFController = BasedGVFController(
        path,
        sunmi.drive,
        kN, kOmega, kF, kS, epsilon, thetaEpsilon, errorMap, epsilonToPID, pidControlEnabled
    )

    val firstDepositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(autoPaths.initPath),
        ProjQuery(Soyeon(sunmi), 0.4)
    )

    fun intakeWithCmd(height: Double) = GVFCmd(
        sunmi.drive,
        genGVFController(
            autoPaths.intakePath,
            kF = 4.5,
            kOmega = 20.0,
            epsilon = 2.0,
            kN = 0.6,
            pidControlEnabled = true,
            epsilonToPID = 6.0
        ),
        ProjQuery(LiftCmd(sunmi.lift, height), 0.2),
    )

    val depositWithCmd = GVFCmd(
        sunmi.drive,
        genGVFController(
            autoPaths.depositPath,
            kF = 6.0,
            kOmega = 20.0,
        ),
        ProjQuery(Soyeon(sunmi), 0.2)
    )

    val leftPark = GVFCmd(sunmi.drive, genGVFController(autoPaths.parkLeftPath))
    val medPark = GVFCmd(sunmi.drive, genGVFController(autoPaths.parkMediumPath), ProjQuery(ArmCmd(sunmi.arm, ArmConstants.home), 0.7))
    val rightPark = GVFCmd(sunmi.drive, genGVFController(autoPaths.parkRightPath))
}