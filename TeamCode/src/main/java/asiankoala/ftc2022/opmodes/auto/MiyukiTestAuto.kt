package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoIntakeSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoReadySeq
import asiankoala.ftc2022.commands.sequence.auto.PreInitCmd
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants.deltaLift
import asiankoala.ftc2022.opmodes.auto.AutoConstants.depositPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.depositProj
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initReadyProj
import asiankoala.ftc2022.opmodes.auto.AutoConstants.intakePath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.intakeProj
import asiankoala.ftc2022.opmodes.auto.AutoConstants.liftHeights
import asiankoala.ftc2022.opmodes.auto.AutoConstants.readyProj
import asiankoala.ftc2022.opmodes.auto.AutoConstants.startPose
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled

@Autonomous
@Disabled
class MiyukiTestAuto : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)

        + SequentialGroup(
            PreInitCmd(miyuki, driver.rightTrigger::isJustPressed),
            WaitUntilCmd { opModeState == OpModeState.START },
            getGVFCmd(
                miyuki,
                initPath,
                Pair(AutoReadySeq(miyuki), ProjQuery(initReadyProj)),
                Pair(AutoDepositSeq(miyuki), ProjQuery(depositProj))
            ),
            *List(5) {
                listOf(
                    getGVFCmd(
                        miyuki,
                        intakePath,
                        Pair(
                            LiftCmd(miyuki.lift, liftHeights[it]),
                            ProjQuery(intakeProj)
                        )
                    ),
                    AutoIntakeSeq(miyuki, liftHeights[it] + deltaLift),
                    getGVFCmd(
                        miyuki,
                        depositPath,
                        Pair(
                            AutoReadySeq(miyuki),
                            ProjQuery(readyProj)
                        ),
                        Pair(
                            AutoDepositSeq(miyuki),
                            ProjQuery(depositProj)
                        )
                    ),
                )
            }.flatten().toTypedArray(),
        )
    }
}