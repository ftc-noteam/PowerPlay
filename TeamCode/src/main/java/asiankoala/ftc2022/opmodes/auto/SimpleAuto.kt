package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.util.OpModeState

abstract class SimpleAuto : KOpMode(photonEnabled = true) {
    protected lateinit var miyuki: Miyuki
    abstract val mainCmd: Cmd

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        + WaitUntilCmd { opModeState == OpModeState.START }
            .andThen(mainCmd)
    }

    override fun mLoop() {
        Logger.addTelemetryData("pose", miyuki.drive.pose)
        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
        Logger.addTelemetryData("arm", miyuki.arm.pos)
    }
}