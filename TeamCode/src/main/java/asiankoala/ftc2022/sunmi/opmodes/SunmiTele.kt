package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.auto.rightSideRobotStartPose
import asiankoala.ftc2022.sunmi.commands.sequence.GIDLE
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.DriveCmd
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

@TeleOp(name = "TELEOP がんばれニールくん！！！(ﾐ꒡ᆽ꒡ﾐ)/ᐠ_ ꞈ _ᐟ\\")
class SunmiTele : KOpMode(photonEnabled = true, maxParallelCommands = 8) {
    private lateinit var sunmi: Sunmi

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi = Sunmi(rightSideRobotStartPose)
        sunmi.vision.unregister()
//        sunmi.odo.unregister()
        sunmi.drive.defaultCommand = DriveCmd(sunmi.drive, driver.leftStick, driver.rightStick)
        configureGamepad()
    }

    private fun configureGamepad() {
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && sunmi.state == State.IDLE) {
                    + GIDLE(sunmi, driver.rightTrigger::isJustPressed)
                        .cancelIf(driver.y::isJustPressed)
                }
            }
        }
        driver.y.onPress(IdleSeq(sunmi))
        driver.rightBumper.onPress(SunmiStratCmd(sunmi, Strategy.MED))
        driver.a.onPress(SunmiStratCmd(sunmi, Strategy.GROUND))
        driver.leftBumper.onPress(SunmiStratCmd(sunmi, Strategy.LOW))
        driver.leftTrigger.onPress(SunmiStratCmd(sunmi, Strategy.HIGH))
        driver.dpadRight.onPress(InstantCmd({ sunmi.stack = min(sunmi.stack + 1, 5) }))
        driver.dpadLeft.onPress(InstantCmd({ sunmi.stack = max(sunmi.stack - 1, 0) }))
        driver.dpadUp.onPress(InstantCmd({ sunmi.isStacking = true }))
        driver.dpadDown.onPress(InstantCmd({ sunmi.isStacking = false }))
    }

    override fun mStart() {
//        + IdleSeq(sunmi)
    }

    override fun mLoop() {
        Logger.addTelemetryData("lift", sunmi.lift.pos)
        Logger.addTelemetryData("strat", sunmi.strat)
        Logger.addTelemetryData("is stacking", sunmi.isStacking)
        Logger.addTelemetryData("stack", sunmi.stack)
        Logger.addTelemetryData("state", sunmi.state)
        Logger.addTelemetryData("sensor", sunmi.claw.lastRead)
        Logger.drawRobot(sunmi.drive.pose)
    }
}