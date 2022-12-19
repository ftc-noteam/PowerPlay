package asiankoala.ftc2022.opmodes.tele

import asiankoala.ftc2022.DepositState
import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.sequence.tele.DepositSeq
import asiankoala.ftc2022.commands.sequence.tele.HomeSeq
import asiankoala.ftc2022.commands.sequence.tele.IntakeSeq
import asiankoala.ftc2022.commands.subsystem.ArmHighCmd
import asiankoala.ftc2022.commands.subsystem.ArmPickupCmd
import asiankoala.ftc2022.commands.subsystem.ClawOpenCmd
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.NVector
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign

@TeleOp
class MiyukiTeleOp : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki
    private var slowMode = false

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        miyuki.odometry.unregister()
        miyuki.vision.unregister()
        driver.leftStick.setDeadzone(0.12)
        driver.rightStick.setDeadzone(0.12)

        scheduleDrive()
        scheduleStrat()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        miyuki.drive.defaultCommand = object : Cmd() {
            val fastScalars = NVector(0.8, 0.8, 0.4)
            val slowScalars = NVector(0.4, 0.4, 0.4)
            val scalars get() = if(slowMode) slowScalars else fastScalars

            private fun joystickFunction(s: Double, k: Double, x: Double): Double {
                return max(0.0, s * x * (k * x.pow(3) - k + 1)) * x.sign
            }

            override fun execute() {
                val raws = NVector(
                    driver.leftStick.xSupplier.invoke(),
                    -driver.leftStick.ySupplier.invoke(),
                    -driver.rightStick.xSupplier.invoke()
                )

                miyuki.drive.powers = raws
                    .mapIndexed { i, d -> joystickFunction(scalars[i], 1.0, d) }
                    .asPose
            }

            init {
                addRequirements(miyuki.drive)
            }
        }

        driver.rightBumper.onPress(InstantCmd({ slowMode = !slowMode }))
    }

    private fun scheduleCycling() {
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && miyuki.state == State.INTAKING) {
                    + IntakeSeq(miyuki, driver.rightTrigger::isJustPressed, driver.leftBumper::isJustPressed, driver.leftTrigger::isJustPressed)
                    Logger.logInfo("scheduled intake sequence")
                }
            }
        }
        + object : Cmd() {
            override fun execute() {
                if(driver.leftTrigger.isJustPressed && miyuki.state == State.DEPOSITING) {
                    + DepositSeq(miyuki)
                    Logger.logInfo("scheduled deposit sequence")
                }
            }
        }
        driver.leftBumper.onPress(ClawOpenCmd(miyuki.claw))
    }

    private fun scheduleStrat() {
        driver.y.onPress(InstantCmd({ miyuki.strategy = DepositState.HIGH }))
        driver.a.onPress(InstantCmd({ miyuki.strategy = DepositState.GROUND }))
        driver.x.onPress(InstantCmd({ miyuki.strategy = DepositState.LOW }))
        driver.b.onPress(InstantCmd({ miyuki.strategy = DepositState.MED }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", miyuki.state)
        Logger.addTelemetryData("strat", miyuki.strategy)
        Logger.addTelemetryData("arm", miyuki.arm.pos)
        Logger.addTelemetryData("lift", miyuki.lift.pos)
        Logger.addTelemetryData("powers", miyuki.drive.powers)
//        Logger.addTelemetryData("power", miyuki.drive.powers)
//        Logger.addTelemetryData("arm power", miyuki.hardware.arm.power)

//        Logger.addTelemetryData("arm power", miyuki.hardware.arm.power)
//        Logger.addVar("arm pos", miyuki.arm.pos)
//        Logger.addVar("arm vel", miyuki.arm.vel)
//        Logger.addVar("arm target pos", miyuki.hardware.arm.setpoint.x)
//        Logger.addVar("arm target vel", miyuki.hardware.arm.setpoint.v)
    }
}
