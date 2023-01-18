package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.sequence.tele.TeleMainSeq
import asiankoala.ftc2022.oryx.commands.subsystem.DriveCmd
import asiankoala.ftc2022.oryx.commands.subsystem.OryxStrategyCmd
import asiankoala.ftc2022.oryx.commands.subsystem.RetractCmd
import asiankoala.ftc2022.oryx.utils.State
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.control.filter.Debouncer
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.min

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class OryxTele : KOpMode(photonEnabled = true) {
    private lateinit var sunmi: Sunmi
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi = Sunmi(Pose())
        sunmi.odo.unregister()
        sunmi.vision.unregister()
        + RetractCmd(sunmi.retract)

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
        sunmi.drive.defaultCommand = DriveCmd(
            sunmi.drive,
            driver.leftStick,
            driver.rightStick,
            driver.rightBumper
        )
    }

    private fun scheduleCycling() {
        driver.rightTrigger.scheduleConditional(TeleMainSeq(sunmi, driver.rightTrigger, driver.leftTrigger)) { sunmi.state == State.INTAKING }
    }

    private fun scheduleStrat() {
        driver.y.onPress(OryxStrategyCmd(sunmi, Strategy.HIGH))
        driver.a.onPress(OryxStrategyCmd(sunmi, Strategy.GROUND))
        driver.x.onPress(OryxStrategyCmd(sunmi, Strategy.LOW))
        driver.b.onPress(OryxStrategyCmd(sunmi, Strategy.MED))

        driver.dpadLeft.onPress(InstantCmd({ sunmi.isStacking = true }))
        driver.dpadRight.onPress(InstantCmd({ sunmi.isStacking = false }))
        driver.dpadUp.onPress(InstantCmd({ sunmi.stackNum = min(5, sunmi.stackNum + 1) }))
        driver.dpadDown.onPress(InstantCmd({ sunmi.stackNum = max(0, sunmi.stackNum - 1) }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", sunmi.state)
        Logger.addTelemetryData("strat", sunmi.strategy)
        Logger.addTelemetryData("is stacking", sunmi.isStacking)
        Logger.addTelemetryData("stack num", sunmi.stackNum)
        Logger.addTelemetryData("arm", sunmi.arm.pos)
        Logger.addTelemetryData("lift", sunmi.lift)
    }
}