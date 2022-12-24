package asiankoala.ftc2022.miyuki.opmodes.auto

import asiankoala.ftc2022.miyuki.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose

abstract class SimpleAuto : KOpMode(photonEnabled = true) {
    protected lateinit var miyuki: Miyuki
    abstract val mainCmd: Cmd

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        + mainCmd
    }

    override fun mStart() {
        miyuki.vision.unregister()
    }

    override fun mLoop() {
        Logger.addTelemetryData("pose", miyuki.drive.pose)
        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
        Logger.addTelemetryData("arm", miyuki.arm.pos)
    }
}