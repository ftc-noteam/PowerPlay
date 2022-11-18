package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.sequence.DepositSequence
import asiankoala.ftc2022.commands.sequence.IntakeSequence
import asiankoala.ftc2022.commands.sequence.ReadySequence
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.L9SpacegliderScript1v9TurboBoostHackCmd
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.Claw
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.subsystem.odometry.Odometry
import com.asiankoala.koawalib.util.Alliance
import com.qualcomm.robotcore.hardware.DcMotor

open class MiyukiTeleOp(private val alliance: Alliance) : KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(Odometry.lastPose) }

    override fun mInit() {
        scheduleDrive()
//        scheduleStrategy()
//        scheduleCycling()
        driver.leftBumper.onPress(ClawCmds.ClawGripCmd(miyuki.claw))
        driver.rightBumper.onPress(ClawCmds.ClawOpenCmd(miyuki.claw))

//        driver.leftBumper.onToggle(InstantCmd({ miyuki.hardware.liftLead.power = 0.07 }))
//        driver.rightBumper.onToggle(InstantCmd({ miyuki.hardware.liftLeft.power = 0.07 }))
//        driver.leftTrigger.onToggle(InstantCmd({ miyuki.hardware.liftBottom.power = 0.07 }))
//        driver.rightTrigger.onToggle(InstantCmd({ miyuki.hardware.arm.power = 0.07 }))
        miyuki.hardware.armEncoder.enable()
    }



    private fun scheduleDrive() {
//        miyuki.drive.defaultCommand = L9SpacegliderScript1v9TurboBoostHackCmd(
//            miyuki.drive,
//            driver.leftStick,
//            driver.rightStick,
//            driver.leftTrigger::isToggled,
//            driver.a::isToggled,
//            miyuki.drive::pose
//        )

        miyuki.testDrive.defaultCommand = MecanumCmd(
            miyuki.testDrive,
            driver.leftStick.yInverted,
            driver.rightStick
        )
    }

//    private fun scheduleStrategy() {
//        driver.leftBumper.onPress(InstantCmd(MiyukiState::incStrat))
//        driver.rightBumper.onPress(InstantCmd(MiyukiState::decStrat))
//    }
//
//    private fun scheduleCycling() {
//        driver.rightTrigger.onPress(
//            InstantCmd({
//                +when (MiyukiState.state) {
//                    MiyukiState.State.INTAKING -> IntakeSequence(miyuki.claw)
//                    MiyukiState.State.READYING -> ReadySequence(miyuki)
//                    MiyukiState.State.DEPOSITING -> DepositSequence(miyuki)
//                }
//            })
//        )
//    }

    override fun mLoop() {
//        Logger.addTelemetryData("state", MiyukiState.state)
//        Logger.addTelemetryData("strat", MiyukiState.strategy)
//        Logger.addTelemetryData("aimbot", driver.a.isToggled)
//        Logger.addTelemetryData("spaceglide", driver.leftTrigger.isToggled)
        miyuki.hardware.armEncoder.update()
        Logger.addTelemetryData("arm", miyuki.hardware.armEncoder.pos)
        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
        Logger.addTelemetryData("left", miyuki.hardware.leftEncoder.pos)
        Logger.addTelemetryData("right", miyuki.hardware.rightEncoder.pos)
        Logger.addTelemetryData("aux", miyuki.hardware.auxEncoder.pos)
    }
}

