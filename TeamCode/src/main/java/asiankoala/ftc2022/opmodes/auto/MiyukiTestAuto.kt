package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.sequence.auto.*
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants.depositPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.depositProjVec
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initReadyProjVec
import asiankoala.ftc2022.opmodes.auto.AutoConstants.intakePath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.intakeProjVec
import asiankoala.ftc2022.opmodes.auto.AutoConstants.liftDeltaHeightToPickupFuckingConeOffStack
import asiankoala.ftc2022.opmodes.auto.AutoConstants.liftHeight
import asiankoala.ftc2022.opmodes.auto.AutoConstants.liftHeights
import asiankoala.ftc2022.opmodes.auto.AutoConstants.readyProjVec
import asiankoala.ftc2022.opmodes.auto.AutoConstants.startPose
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.path.ProjQuery
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
            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, AutoConstants.startPose),
            WaitUntilCmd { opModeState == OpModeState.START },
            getGVFCmd(
                miyuki,
                initPath,
                Pair(AutoReadySeq(miyuki), ProjQuery(initReadyProjVec)),
                Pair(AutoDepositSeq(miyuki, liftHeight), ProjQuery(depositProjVec))
            ),
            *List(5) {
                listOf(
                    getGVFCmd(
                        miyuki,
                        intakePath,
                        Pair(
                            LiftCmd(miyuki.lift, liftHeights[it]),
                            ProjQuery(intakeProjVec)
                        )
                    ),
                    AutoIntakeSeq(miyuki, liftHeights[it] + liftDeltaHeightToPickupFuckingConeOffStack),
                    getGVFCmd(
                        miyuki,
                        depositPath,
                        Pair(
                            AutoReadySeq(miyuki),
                            ProjQuery(readyProjVec)
                        ),
                        Pair(
                            AutoDepositSeq(miyuki, liftHeights[it]),
                            ProjQuery(depositProjVec)
                        )
                    ),
                )
            }.flatten().toTypedArray(),
        )
    }
}