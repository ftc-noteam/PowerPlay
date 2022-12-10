package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
import asiankoala.ftc2022.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoReadySeq
import asiankoala.ftc2022.commands.sequence.auto.ParkCmd
import asiankoala.ftc2022.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants.middlePath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.choose
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.leftPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.rightPath
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
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
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.util.Alliance
import com.asiankoala.koawalib.util.OpModeState

open class MiyukiAuto(private val alliance: Alliance, private val far: Boolean) : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    private val startPose = AutoConstants.startPose.choose(alliance, far)
    private val pathStart = startPose.copy(heading = 0.0)
//    private val initPath = AutoConstants.initPath.choose(alliance, far)
//    private val initIntakePath = AutoConstants.initIntakePath.choose(alliance, far)
//    private val depositPath = AutoConstants.depositPath.choose(alliance, far)
//    private val intakePath = AutoConstants.intakePath.choose(alliance, far)
//
//    private val intakeProj = AutoConstants.intakeProj.choose(alliance, far)
//    private val readyProj = AutoConstants.readyProj.choose(alliance, far)
//
//    private val parkLeftPath = AutoConstants.parkLeftPath.choose(alliance, far)
//    private val parkRightPath = AutoConstants.parkRightPath.choose(alliance, far)

    private val leftPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        pathStart,
        Pose(-48.0, -36.0, 0.0),
        Pose(-36.0, -12.0, 90.0.radians)
    ).choose(alliance, far)

    private val middlePath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        pathStart,
        Pose(-36.0, -36.0, 0.0)
    ).choose(alliance, far)

    private val rightPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        pathStart,
        Pose(-48.0, -36.0, 0.0),
        Pose(-36.0, -60.0, 270.0.radians)
    ).choose(alliance, far)

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)
        miyuki.vision.start()
//        val initReadyProj = Pair(AutoReadySeq(miyuki), ProjQuery(AutoConstants.initReadyProj.choose(alliance, far)))
//        val depositProj = Pair(AutoDepositSeq(miyuki, AutoConstants.liftHeight), ProjQuery(AutoConstants.depositProj.choose(alliance, far), 0.95))
        + SequentialGroup(
            ChooseCmd(
                getGVFCmd(miyuki, leftPath),
                ChooseCmd(
                    getGVFCmd(miyuki, middlePath),
                    getGVFCmd(miyuki, rightPath)
                ) { miyuki.vision.zone == Zones.MIDDLE },
            ) { miyuki.vision.zone == Zones.LEFT }
//            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, startPose),
//            WaitUntilCmd { opModeState == OpModeState.START },
//
//            AutoConstants.getGVFCmd(miyuki, initPath, initReadyProj).andPause(0.5),
//            ClawDepositCmd(miyuki.claw)
//                .andPause(1.0),
//            ClawGripCmd(miyuki.claw)
//                .andPause(1.0),
//            ParallelGroup(
//                PivotHomeCmd(miyuki.pivot),
//                LiftHomeCmd(miyuki.lift),
//                ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
//                ArmPickupCmd(miyuki.arm)
//            ),
//
//            WaitCmd(1.0),
//            ParkCmd(miyuki, miyuki.vision::zone)
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