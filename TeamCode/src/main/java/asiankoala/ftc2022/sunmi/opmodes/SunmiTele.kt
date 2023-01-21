package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.tele.GIDLE
import asiankoala.ftc2022.sunmi.commands.subsystem.SunmiStratCmd
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class SunmiTele : KOpMode(photonEnabled = true) {
    private val sunmi by lazy { Sunmi(Pose()) }
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi.drive.defaultCommand = MecanumCmd(sunmi.drive, driver.leftStick, driver.rightStick, rScalar = 0.5)
        driver.rightTrigger.onPress(GIDLE(sunmi, driver.rightBumper))

        driver.y.onPress(SunmiStratCmd(sunmi, Strategy.HIGH))
        driver.a.onPress(SunmiStratCmd(sunmi, Strategy.GROUND))
        driver.x.onPress(SunmiStratCmd(sunmi, Strategy.LOW))
        driver.b.onPress(SunmiStratCmd(sunmi, Strategy.MED))
    }

    override fun mLoop() {
        Logger.addTelemetryData("lift", sunmi.lift.pos)
    }
}