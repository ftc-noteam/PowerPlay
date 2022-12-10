package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
import asiankoala.ftc2022.commands.sequence.auto.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class MiyukiAuto : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    private val startPose = AutoConstants.startPose
    private val pathStart = startPose.copy(heading = 0.0)

    private val leftPath = HermitePath(
        { 90.0.radians },
        pathStart,
        Pose(-40.0, -36.0, 0.0),
        Pose(-36.0, -14.0, 90.0.radians)
    )

    private val middlePath = HermitePath(
        { 90.0.radians },
        pathStart,
        Pose(-36.0, -36.0, 0.0)
    )

    private val rightPath = HermitePath(
        { 90.0.radians },
        pathStart,
        Pose(-40.0, -36.0, 0.0),
        Pose(-36.0, -58.0, 270.0.radians)
    )

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)
        miyuki.vision.start()
        + SequentialGroup(
            MakeLifeEasierCmd(miyuki, driver.rightTrigger::isJustPressed, startPose),
            WaitUntilCmd { opModeState == OpModeState.START },
            ChooseCmd(
                getGVFCmd(miyuki, leftPath),
                ChooseCmd(
                    getGVFCmd(miyuki, middlePath),
                    getGVFCmd(miyuki, rightPath)
                ) { miyuki.vision.zone == Zones.MIDDLE },
            ) { miyuki.vision.zone == Zones.LEFT }
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