package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.sequence.DepositSequence
import asiankoala.ftc2022.commands.sequence.IntakeSequence
import asiankoala.ftc2022.commands.sequence.ReadySequence
import asiankoala.ftc2022.commands.subsystem.L9SpacegliderScript1v9TurboBoostHackCmd
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.subsystem.odometry.Odometry
import com.asiankoala.koawalib.util.Alliance

open class MiyukiTeleOp(private val alliance: Alliance) : KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(Odometry.lastPose) }

    override fun mInit() {
        scheduleDrive()
        scheduleStrategy()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        miyuki.drive.defaultCommand = L9SpacegliderScript1v9TurboBoostHackCmd(
            miyuki.drive,
            driver.leftStick,
            driver.rightStick,
            driver.leftTrigger::isToggled,
            driver.a::isToggled,
            miyuki.drive::pose
        )
    }

    private fun scheduleStrategy() {
        driver.leftBumper.onPress(InstantCmd(MiyukiState::incStrat))
        driver.rightBumper.onPress(InstantCmd(MiyukiState::decStrat))
    }

    private fun scheduleCycling() {
        driver.rightTrigger.onPress(
            InstantCmd({
                +when (MiyukiState.state) {
                    MiyukiState.State.INTAKING -> IntakeSequence(miyuki.claw)
                    MiyukiState.State.READYING -> ReadySequence(miyuki)
                    MiyukiState.State.DEPOSITING -> DepositSequence(miyuki)
                }
            })
        )
    }

    override fun mLoop() {
        Logger.addTelemetryData("state", MiyukiState.state)
        Logger.addTelemetryData("strat", MiyukiState.strategy)
        Logger.addTelemetryData("aimbot", driver.a.isToggled)
        Logger.addTelemetryData("spaceglide", driver.leftTrigger.isToggled)
    }
}

