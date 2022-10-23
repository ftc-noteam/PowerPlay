package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.sequence.DepositSequence
import asiankoala.ftc2022.commands.sequence.IntakeSequence
import asiankoala.ftc2022.commands.sequence.ReadySequence
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.odometry.Odometry

class MiyukiTeleOp: KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(Odometry.lastPose) }

    override fun mInit() {
        scheduleDrive()
        scheduleStrategy()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        miyuki.drive.defaultCommand = MecanumCmd(
            miyuki.drive,
            driver.leftStick,
            driver.rightStick,
            0.5,
            0.5,
            0.5,
            1.0,
            1.0,
            1.0,
            MiyukiState.alliance,
            isTranslationFieldCentric = true,
            isHeadingFieldCentric = true,
            { miyuki.drive.pose.heading },
            60.0.radians
        )

        driver.a.onToggle(
            InstantCmd({ miyuki.l9SpacegliderScript1v9TurboBoostHack.aimbot(driver.leftStick::vector) })
        )

        driver.leftTrigger.onToggle(
            InstantCmd({ miyuki.l9SpacegliderScript1v9TurboBoostHack.spaceglide(driver.leftStick::vector) })
        )
    }

    private fun scheduleStrategy() {
        driver.leftBumper.onPress(InstantCmd(MiyukiState::incStrat))
        driver.rightBumper.onPress(InstantCmd(MiyukiState::decStrat))
    }

    private fun scheduleCycling() {
        driver.rightTrigger.onPress(
            ChooseCmd(
                IntakeSequence(miyuki.claw)
                    .andThen(ReadySequence(miyuki)),
                DepositSequence(miyuki)
            ) { MiyukiState.state == MiyukiState.State.INTAKING }
        )
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", MiyukiState.state)
        Logger.addTelemetryData("strat", MiyukiState.strategy)
        Logger.addTelemetryData("aimbot", driver.a.isToggled)
        Logger.addTelemetryData("spaceglide", driver.leftTrigger.isToggled)
    }
}

