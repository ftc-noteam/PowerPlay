package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.GIDLE
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.SunmiStratCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sign

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class SunmiTele : KOpMode(photonEnabled = true) {
    private val sunmi by lazy { Sunmi(Pose()) }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi.vision.unregister()
        sunmi.odo.unregister()

        driver.leftStick.setXRateLimiter(DriveConstants.xLimiter)
        driver.leftStick.setYRateLimiter(DriveConstants.yLimiter)
        driver.rightStick.setXRateLimiter(DriveConstants.rLimiter)
        driver.y.onPress(SunmiStratCmd(sunmi, Strategy.MED))
        driver.a.onPress(SunmiStratCmd(sunmi, Strategy.GROUND))
        driver.x.onPress(SunmiStratCmd(sunmi, Strategy.LOW))
        driver.b.onPress(SunmiStratCmd(sunmi, Strategy.HIGH))
        driver.dpadRight.onPress(InstantCmd({ sunmi.stack = min(sunmi.stack + 1, 5) }))
        driver.dpadLeft.onPress(InstantCmd({ sunmi.stack = max(sunmi.stack - 1, 0) }))
        driver.dpadUp.onPress(InstantCmd({ sunmi.isStacking = true }))
        driver.dpadDown.onPress(InstantCmd({ sunmi.isStacking = false }))

        sunmi.drive.defaultCommand = object : Cmd() {
            val fastScalars = listOf(1.0, 1.0, 0.85)

            private fun joystickFunction(s: Double, k: Double, x: Double): Double {
                return max(0.0, s * x * (k * x.pow(3) - k + 1)) * x.sign
            }

            override fun execute() {
                val raws = listOf(
                    driver.leftStick.xAxis,
                    -driver.leftStick.yAxis,
                    -driver.rightStick.xAxis
                )

                sunmi.drive.powers = raws
                    .mapIndexed { i, d -> joystickFunction(fastScalars[i], 1.0, d) }
                    .let { Pose(it[0], it[1], it[2]) }
            }

            init {
                addRequirements(sunmi.drive)
            }
        }

        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && sunmi.state == State.IDLE) {
                    + GIDLE(sunmi, driver.rightTrigger::isJustPressed)
                    Logger.logInfo("scheduled intake sequence")
                }
            }
        }
    }

    override fun mStart() {
        + IdleSeq(sunmi)
    }

    override fun mLoop() {
        Logger.addTelemetryData("lift", sunmi.lift.pos)
        Logger.addTelemetryData("strat", sunmi.strat)
        Logger.addTelemetryData("is stacking", sunmi.isStacking)
        Logger.addTelemetryData("stack", sunmi.stack)
        Logger.addTelemetryData("state", sunmi.state)
        Logger.addTelemetryData("sensor", sunmi.claw.lastRead)
    }
}