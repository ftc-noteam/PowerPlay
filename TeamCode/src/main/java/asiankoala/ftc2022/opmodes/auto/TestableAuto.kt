package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose

abstract class TestableAuto : KOpMode(photonEnabled = true) {
    protected lateinit var miyuki: Miyuki
    abstract val mainCmd: Cmd

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        + mainCmd
    }
}