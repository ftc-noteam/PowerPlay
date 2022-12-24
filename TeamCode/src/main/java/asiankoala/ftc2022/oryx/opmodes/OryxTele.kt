package asiankoala.ftc2022.oryx.opmodes

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.sequence.DepositSeq
import asiankoala.ftc2022.oryx.commands.sequence.IntakeSeq
import asiankoala.ftc2022.oryx.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.oryx.utils.State
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "TELE がんばれニールくん！！！")
class OryxTele : KOpMode(photonEnabled = true) {
    private lateinit var oryx: Oryx
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        oryx.odo.unregister()
        oryx.vision.unregister()
        driver.leftStick.setDeadzone(0.1)
        driver.rightStick.setDeadzone(0.1)
    }

    private fun scheduleDrive() {
        oryx.drive.defaultCommand = MecanumCmd(
            oryx.drive,
            driver.leftStick,
            driver.rightStick
        )
    }

    private fun scheduleCycling() {
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && oryx.state == State.INTAKING) {
                    + IntakeSeq(oryx, driver.rightTrigger, driver.leftBumper, driver.leftTrigger)
                    Logger.logInfo("scheduled intake seq")
                }
            }
        }

        + object : Cmd() {
            override fun execute() {
                if(driver.leftTrigger.isJustPressed && oryx.state == State.DEPOSITING) {
                    + DepositSeq(oryx)
                    Logger.logInfo("scheduled deposit seq")
                }
            }
        }

        driver.leftBumper.onPress(ClawOpenCmd(oryx.claw))
    }


    private fun scheduleStrat() {
        driver.y.onPress(InstantCmd({ oryx.strategy = Strategy.HIGH }))
        driver.a.onPress(InstantCmd({ oryx.strategy = Strategy.GROUND }))
        driver.x.onPress(InstantCmd({ oryx.strategy = Strategy.LOW }))
        driver.b.onPress(InstantCmd({ oryx.strategy = Strategy.MED }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", oryx.state)
        Logger.addTelemetryData("strat", oryx.strategy)
        Logger.addTelemetryData("arm", oryx.arm.pos)
        Logger.addTelemetryData("lift", oryx.lift)
    }
}