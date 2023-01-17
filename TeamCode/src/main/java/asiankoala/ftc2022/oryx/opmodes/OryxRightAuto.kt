package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.oryx.commands.sequence.auto.AutoIntakeSeq
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.utils.Strategy
import asiankoala.ftc2022.oryx.utils.Zones
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HeadingController
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Right")
open class OryxRightAuto : KOpMode(true) {
    private lateinit var oryx: Oryx
    protected open val start = Pose(-63.0, -36.0, 0.0)
    protected open val initReady = Pose(-24.0, -36.0, 0.0)
    protected open val deposit = Pose(-6.0, -30.0, 40.0.radians)
    protected open val intake = Pose(-12.0, -60.0, 270.0.radians)

    private fun getGvfCmd(
        path: HermitePath,
        vararg cmds: ProjQuery,
        kN: Double = 0.6,
        kOmega: Double = 30.0,
        kF: Double = 4.0,
        kS: Double = 0.8,
        epsilon: Double = 2.0,
        thetaEpsilon: Double = 2.0
    ) = GVFCmd(
        oryx.drive,
        SimpleGVFController(path, oryx.drive, kN, kOmega, kF, kS, epsilon, thetaEpsilon),
        *cmds
    )

    private val initDepositPath by lazy {
        HermitePath(
            DEFAULT_HEADING_CONTROLLER,
            start,
            initReady,
            deposit
        )
    }
    private val intakePath by lazy {
        HermitePath(
            FLIPPED_HEADING_CONTROLLER,
            deposit,
            intake
        )
    }
    private val depositPath = intakePath.flip()
    private val initCmd = getGvfCmd(
        initDepositPath,
        ProjQuery(ParallelGroup(
            LiftStateCmd(oryx.lift, Strategy.HIGH),
            PivotStateCmd(oryx.pivot, Strategy.HIGH),
            ArmStateCmd(oryx.arm, Strategy.HIGH)
        ), 0.5),
        ProjQuery(AutoDepositSeq(oryx), 0.99)
    )
    private val intakeCmd = getGvfCmd(intakePath, ProjQuery(AutoIntakeSeq(oryx), 0.99))
    private val depositCmd = getGvfCmd(
        depositPath,
        ProjQuery(LiftStateCmd(oryx.lift, Strategy.HIGH), 0.5),
        ProjQuery(AutoDepositSeq(oryx), 0.99)
    )
    private val endCmd by lazy {
        ChooseCmd(
            getGvfCmd(
                HermitePath(
                    { 90.0 },
                    deposit.copy(heading = 90.0.radians),
                    deposit.copy(y = deposit.y + 18.0, heading = 90.0.radians)
                ),
                ProjQuery(LiftHomeCmd(oryx.lift), 0.5)
            ),
            ChooseCmd(
                LiftHomeCmd(oryx.lift),
                getGvfCmd(
                    HermitePath(
                        { 90.0 },
                        deposit.copy(heading = 270.0.radians),
                        deposit.copy(y = deposit.y - 36.0, heading = 270.0.radians)
                    ),
                    ProjQuery(LiftHomeCmd(oryx.lift), 0.5)
                )
            ) { oryx.vision.zone == Zones.MIDDLE }
        ) { oryx.vision.zone == Zones.LEFT }
    }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        oryx = Oryx(start)

        + SequentialGroup(
            ClawOpenCmd(oryx.claw),
            ArmCmd(oryx.arm, 90.0),
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
            ClawGripCmd(oryx.claw),
            WaitUntilCmd { opModeState == OpModeState.START },
            initCmd,
            WaitCmd(3.0),
            intakeCmd,
            WaitCmd(3.0),
            depositCmd,
            WaitCmd(3.0),
            endCmd
        )
    }

    override fun mInitLoop() {
        Logger.addTelemetryData("zone", oryx.vision.zone)
    }
}