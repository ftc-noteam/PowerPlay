package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class ClawTest : KOpMode(photonEnabled = true, maxParallelCommands = 8) {
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        val claw = KServo("claw")
            .startAt(ClawTestConstants.open)
        driver.rightTrigger.onPress(InstantCmd({ claw.position = ClawTestConstants.open }))
        driver.leftTrigger.onPress(InstantCmd({ claw.position = ClawTestConstants.closed }))
    }
}