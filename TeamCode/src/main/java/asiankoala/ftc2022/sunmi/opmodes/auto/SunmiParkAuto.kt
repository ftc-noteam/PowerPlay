package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.Zones
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class SunmiParkAuto : KOpMode(photonEnabled = true) {
    private lateinit var sunmi: Sunmi

    private val startPose = Pose(-63.0, -36.0, 180.0.radians)
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
    private val kN = 0.4
    private val kOmega = 45.0
    private val kF = 4.0
    private val kS = 0.5
    private val epsilon = 2.0
    private val thetaEpsilon = 5.0

    fun getGVFCmd(
        sunmi: Sunmi,
        path: Path
    ) = GVFCmd(sunmi.drive, SimpleGVFController(path, sunmi.drive, kN, kOmega, kF, kS, epsilon, thetaEpsilon))

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi = Sunmi(startPose)
        sunmi.vision.start()
        + SequentialGroup(
            WaitUntilCmd { opModeState == OpModeState.START },
            ChooseCmd(
                getGVFCmd(sunmi, leftPath),
                ChooseCmd(
                    getGVFCmd(sunmi, middlePath),
                    getGVFCmd(sunmi, rightPath)
                ) { sunmi.vision.zone == Zones.MIDDLE },
            ) { sunmi.vision.zone == Zones.LEFT }
        )
    }

    override fun mInitLoop() {
        Logger.addTelemetryData("zone", sunmi.vision.zone)
    }

    override fun mStart() {
        sunmi.vision.stop()
        sunmi.vision.unregister()
    }

    override fun mLoop() {
        Logger.addTelemetryData("zone", sunmi.vision.zone)
    }
}