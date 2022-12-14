package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.sequence.teleop.DepositSeq
import asiankoala.ftc2022.commands.sequence.teleop.IntakeSeq
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class MiyukiTeleOp : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        miyuki.hardware.odometry.unregister()
        miyuki.vision.unregister()
        scheduleDrive()
        scheduleStrat()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        miyuki.drive.defaultCommand = MecanumCmd(
            miyuki.drive,
            driver.leftStick.xInverted,
            driver.rightStick,
            xScalar = 0.7,
            yScalar = 0.7,
            rScalar = 0.5,
        )
    }

    private fun scheduleCycling() {
        + object : Cmd() {
            override fun execute() {
                // left trigger is used to transition between intake and ready sequence
                // so schedule this command only when we're intaking
                if(driver.leftTrigger.isJustPressed && miyuki.state == State.INTAKING) {
                    + IntakeSeq(miyuki, driver.leftTrigger::isJustPressed)
                        .cancelIf(driver.rightTrigger::isJustPressed)
                }
            }
        }
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && miyuki.state == State.DEPOSITING) {
                    + DepositSeq(miyuki, driver.rightTrigger::isJustPressed)
                }
            }
        }
    }

    private fun scheduleStrat() {
        driver.leftBumper.onPress(InstantCmd(miyuki::decStrat))
        driver.rightBumper.onPress(InstantCmd(miyuki::incStrat))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", miyuki.state)
        Logger.addTelemetryData("strat", miyuki.strategy)
        Logger.addTelemetryData("arm", miyuki.hardware.arm.pos)
        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
//        Logger.addVar("lift pos", miyuki.lift.pos)
//        Logger.addVar("lift vel", miyuki.lift.vel)
//        Logger.addVar("target pos", miyuki.hardware.liftLead.setpoint.x)
//        Logger.addVar("target vel", miyuki.hardware.liftLead.setpoint.v)
    }
}
