package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.miyuki.commands.sequence.tele.DriveCmd
import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.sequence.TeleMainSeq
import asiankoala.ftc2022.oryx.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.oryx.commands.subsystem.OryxStrategyCmd
import asiankoala.ftc2022.oryx.utils.State
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WatchdogCmd
import com.asiankoala.koawalib.control.filter.Debouncer
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.min

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class OryxTele : KOpMode(photonEnabled = true) {
    private lateinit var oryx: Oryx
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        oryx = Oryx(Pose(), true)
        oryx.odo.unregister()
        oryx.vision.unregister()

        configureControls()
        scheduleDrive()
        scheduleCycling()
        scheduleStrat()
    }

    private fun configureControls() {
        driver.leftStick.setDeadzone(0.1)
        driver.rightStick.setDeadzone(0.1)
        driver.leftTrigger.setDebouncer(Debouncer(0.3))
        driver.rightTrigger.setDebouncer(Debouncer(0.3))
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
        + WatchdogCmd(TeleMainSeq(oryx, driver.rightTrigger, driver.leftTrigger)) { driver.rightTrigger.isJustPressed && oryx.state == State.INTAKING }
        driver.leftTrigger.onPress(ClawOpenCmd(oryx.claw))
    }

    private fun scheduleStrat() {
        driver.y.onPress(OryxStrategyCmd(oryx, Strategy.HIGH))
        driver.a.onPress(OryxStrategyCmd(oryx, Strategy.GROUND))
        driver.x.onPress(OryxStrategyCmd(oryx, Strategy.LOW))
        driver.b.onPress(OryxStrategyCmd(oryx, Strategy.MED))

        driver.leftBumper.onPress(InstantCmd({ oryx.isStacking = !oryx.isStacking}))
        driver.dpadUp.onPress(InstantCmd({ oryx.stackNum = min(4, oryx.stackNum + 1) }))
        driver.dpadDown.onPress(InstantCmd({ oryx.stackNum = max(0, oryx.stackNum - 1) }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", oryx.state)
        Logger.addTelemetryData("strat", oryx.strategy)
        Logger.addTelemetryData("arm", oryx.arm.pos)
        Logger.addTelemetryData("lift", oryx.lift)
    }
}