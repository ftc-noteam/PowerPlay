package asiankoala.ftc2022.opmodes.tele

import asiankoala.ftc2022.DepositState
import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.sequence.tele.DepositSeq
import asiankoala.ftc2022.commands.sequence.tele.HomeSeq
import asiankoala.ftc2022.commands.sequence.tele.IntakeSeq
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
        driver.leftStick.setDeadzone(0.1)
        driver.rightStick.setDeadzone(0.1)

        scheduleDrive()
        scheduleStrat()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        miyuki.drive.defaultCommand = MecanumCmd(
            miyuki.drive,
            driver.leftStick,
            driver.rightStick,
            xScalar = 0.7,
            yScalar = 0.7,
            rScalar = 0.5,
        )

        driver.rightBumper.onToggle(MecanumCmd(
            miyuki.drive,
            driver.leftStick,
            driver.rightStick,
            xScalar = 0.3,
            yScalar = 0.3,
            rScalar = 0.3
        ))
    }

    private fun scheduleCycling() {
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && miyuki.state == State.INTAKING) {
                    + IntakeSeq(miyuki, driver.leftTrigger::isJustPressed)
                        .cancelIf(driver.leftBumper::isJustPressed)
                }
            }
        }
        + object : Cmd() {
            override fun execute() {
                if(driver.leftTrigger.isJustPressed && miyuki.state == State.DEPOSITING) {
                    + DepositSeq(miyuki, driver.rightTrigger::isJustPressed)
                        .cancelIf(driver.leftBumper::isJustPressed)
                }
            }
        }

        driver.leftBumper.onPress(HomeSeq(miyuki))
    }

    private fun scheduleStrat() {
//        driver.leftBumper.onPress(InstantCmd(miyuki::decStrat))
//        driver.rightBumper.onPress(InstantCmd(miyuki::incStrat))
        driver.y.onPress(InstantCmd({ miyuki.strategy = DepositState.HIGH }))
        driver.a.onPress(InstantCmd({ miyuki.strategy = DepositState.GROUND }))
        driver.x.onPress(InstantCmd({ miyuki.strategy = DepositState.LOW }))
        driver.b.onPress(InstantCmd({ miyuki.strategy = DepositState.MED }))
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
