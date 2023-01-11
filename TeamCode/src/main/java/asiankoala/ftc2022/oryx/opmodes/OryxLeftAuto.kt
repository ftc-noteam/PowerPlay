package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.sequence.auto.AutoDepositSeq
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController

class OryxLeftAuto : KOpMode(true) {
    private lateinit var oryx: Oryx
    private val start = Pose(-63.0, -36.0, 0.0)
    private val deposit = Pose(-6.0, -30.0, 40.0.radians)
    private val intake = Pose(-12.0, -60.0, 270.0.radians)
    private fun getGvfCmd(
        path: HermitePath,
        vararg cmds: ProjQuery,
        kN: Double = 0.6,
        kOmega: Double = 30.0,
        kF: Double = 4.0,
        kS: Double = 0.8,
        epsilon: Double = 2.0,
        thetaEpsilon: Double = 2.0
    ) = GVFCmd(oryx.drive, SimpleGVFController(
        path,
        oryx.drive,
        kN,
        kOmega,
        kF,
        kS,
        epsilon,
        thetaEpsilon
    ))

    private val initDepositPath = HermitePath(
        DEFAULT_HEADING_CONTROLLER,
        start,
        Pose(-24.0, -36.0, 0.0),
        deposit
    )

    private val intakePath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        deposit,
        intake
    )

    private val depositPath = intakePath.flip()

    private val initCmd = getGvfCmd(
        initDepositPath,
        ProjQuery(AutoDepositSeq(oryx), 0.5)
    )

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        oryx = Oryx(start)
    }
}