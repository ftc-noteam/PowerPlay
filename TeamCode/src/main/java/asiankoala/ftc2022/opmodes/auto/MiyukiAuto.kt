package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
import asiankoala.ftc2022.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoReadySeq
import asiankoala.ftc2022.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.opmodes.auto.AutoConstants.choose
import asiankoala.ftc2022.opmodes.auto.AutoConstants.parkRightPath
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.util.Alliance
import com.asiankoala.koawalib.util.OpModeState

open class MiyukiAuto(alliance: Alliance, far: Boolean) : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    private val startPose = AutoConstants.startPose.choose(alliance, far)
    private val initPath = AutoConstants.initPath.choose(alliance, far)
    private val initIntakePath = AutoConstants.initIntakePath.choose(alliance, far)
    private val depositPath = AutoConstants.depositPath.choose(alliance, far)
    private val intakePath = AutoConstants.intakePath.choose(alliance, far)

    private val initReadyProj = Pair(AutoReadySeq(miyuki), ProjQuery(AutoConstants.initReadyProj.choose(alliance, far)))
    private val depositProj = Pair(AutoDepositSeq(miyuki, AutoConstants.liftHeight), ProjQuery(AutoConstants.depositProj.choose(alliance, far)))
    private val intakeProj = AutoConstants.intakeProj.choose(alliance, far)
    private val readyProj = AutoConstants.readyProj.choose(alliance, far)

    private val parkLeftPath = AutoConstants.parkLeftPath.choose(alliance, far)
    private val parkRightPath = AutoConstants.parkRightPath.choose(alliance, far)

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)
        + SequentialGroup(
            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, startPose),
            WaitUntilCmd { opModeState == OpModeState.START },

            AutoConstants.getGVFCmd(miyuki, initPath, initReadyProj, depositProj).andPause(0.5),


            // now we park
            when(miyuki.vision.zone) {
                Zones.LEFT -> AutoConstants.getGVFCmd(miyuki, parkLeftPath)
                Zones.RIGHT -> AutoConstants.getGVFCmd(miyuki, parkRightPath)
                else -> WaitCmd(0.5)
            }
//            getGVFCmd(miyuki, initIntakePath).andPause(0.5),
//
//            getGVFCmd(miyuki, depositPath).andPause(0.5),
//            getGVFCmd(miyuki, intakePath).andPause(0.5)


        )
    }
}