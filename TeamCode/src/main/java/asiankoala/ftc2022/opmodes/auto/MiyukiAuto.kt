package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
import asiankoala.ftc2022.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoReadySeq
import asiankoala.ftc2022.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants.middlePath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.choose
import asiankoala.ftc2022.opmodes.auto.AutoConstants.leftPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.rightPath
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.util.Alliance
import com.asiankoala.koawalib.util.OpModeState

open class MiyukiAuto(private val alliance: Alliance, private val far: Boolean) : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    private val startPose = AutoConstants.startPose.choose(alliance, far)
    private val initPath = AutoConstants.initPath.choose(alliance, far)
    private val initIntakePath = AutoConstants.initIntakePath.choose(alliance, far)
    private val depositPath = AutoConstants.depositPath.choose(alliance, far)
    private val intakePath = AutoConstants.intakePath.choose(alliance, far)

    private val intakeProj = AutoConstants.intakeProj.choose(alliance, far)
    private val readyProj = AutoConstants.readyProj.choose(alliance, far)

    private val parkLeftPath = AutoConstants.parkLeftPath.choose(alliance, far)
    private val parkRightPath = AutoConstants.parkRightPath.choose(alliance, far)

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)
        miyuki.vision.start()
        val initReadyProj = Pair(AutoReadySeq(miyuki), ProjQuery(AutoConstants.initReadyProj.choose(alliance, far)))
        val depositProj = Pair(AutoDepositSeq(miyuki, AutoConstants.liftHeight), ProjQuery(AutoConstants.depositProj.choose(alliance, far), 0.95))
        + SequentialGroup(
            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, startPose),
            WaitUntilCmd { opModeState == OpModeState.START },

            AutoConstants.getGVFCmd(miyuki, initPath, initReadyProj).andPause(0.5),
            ClawDepositCmd(miyuki.claw)
                .andPause(1.0),
            ClawGripCmd(miyuki.claw)
                .andPause(1.0),
            ParallelGroup(
                PivotHomeCmd(miyuki.pivot),
                LiftHomeCmd(miyuki.lift),
                ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
                ArmPickupCmd(miyuki.arm)
            ),

            WaitCmd(1.0),

            // now we park
            when(miyuki.vision.zone) {
                Zones.LEFT -> AutoConstants.getGVFCmd(miyuki, leftPath)
                Zones.MIDDLE -> AutoConstants.getGVFCmd(miyuki, middlePath)
                Zones.RIGHT -> AutoConstants.getGVFCmd(miyuki, rightPath)
                else -> WaitCmd(0.5)
            }
        )
    }

    override fun mInitLoop() {
        Logger.addTelemetryData("zone", miyuki.vision.zone)
    }

    override fun mStart() {
        miyuki.vision.stop()
        miyuki.vision.unregister()
    }

    override fun mLoop() {
        Logger.addTelemetryData("zone", miyuki.vision.zone)
    }
}