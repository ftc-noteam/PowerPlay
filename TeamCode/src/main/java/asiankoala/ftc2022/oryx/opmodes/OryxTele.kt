package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.miyuki.commands.sequence.tele.DriveCmd
import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.sequence.DepositSeq
import asiankoala.ftc2022.oryx.commands.sequence.IntakeSeq
import asiankoala.ftc2022.oryx.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.oryx.commands.subsystem.OryxStrategyCmd
import asiankoala.ftc2022.oryx.utils.State
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.WatchdogCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class OryxTele : KOpMode(photonEnabled = true) {
    private lateinit var oryx: Oryx
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        oryx = Oryx(Pose(), true)
        oryx.odo.unregister()
        oryx.vision.unregister()
        driver.leftStick.setDeadzone(0.1)
        driver.rightStick.setDeadzone(0.1)
    }

    private fun scheduleDrive() {
        oryx.drive.defaultCommand = DriveCmd(
            oryx.drive,
            driver.leftStick,
            driver.rightStick,
            driver.rightBumper
        )
    }

    private fun scheduleCycling() {
        + WatchdogCmd(IntakeSeq(oryx, driver.rightTrigger, driver.leftBumper)) { driver.rightTrigger.isJustPressed && oryx.state == State.INTAKING }
        + WatchdogCmd(DepositSeq(oryx)) { driver.leftTrigger.isJustPressed && oryx.state == State.DEPOSITING }
        driver.leftBumper.onPress(ClawOpenCmd(oryx.claw))
    }


    private fun scheduleStrat() {
        driver.y.onPress(OryxStrategyCmd(oryx, Strategy.HIGH))
        driver.a.onPress(OryxStrategyCmd(oryx, Strategy.GROUND))
        driver.x.onPress(OryxStrategyCmd(oryx, Strategy.LOW))
        driver.b.onPress(OryxStrategyCmd(oryx, Strategy.MED))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", oryx.state)
        Logger.addTelemetryData("strat", oryx.strategy)
        Logger.addTelemetryData("arm", oryx.arm.pos)
        Logger.addTelemetryData("lift", oryx.lift)
    }
}