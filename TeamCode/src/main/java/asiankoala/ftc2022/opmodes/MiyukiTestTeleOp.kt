package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.ArmConstants
import asiankoala.ftc2022.subsystems.LiftConstants
import asiankoala.ftc2022.subsystems.PivotConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.subsystem.odometry.Odometry
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class MiyukiTestTeleOp : KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(Odometry.lastPose) }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
//        scheduleDrive()
//        scheduleStrategy()
//        scheduleCycling()
//        driver.leftBumper.onPress(ClawCmds.ClawGripCmd(miyuki.claw))
//        driver.rightBumper.onPress(ClawCmds.ClawOpenCmd(miyuki.claw))

//        driver.leftBumper.onToggle(InstantCmd({ miyuki.hardware.liftLead.power = 0.07 }))
//        driver.rightBumper.onToggle(InstantCmd({ miyuki.hardware.liftLeft.power = 0.07 }))
//        driver.leftTrigger.onToggle(InstantCmd({ miyuki.hardware.liftBottom.power = 0.07 }))
//        driver.rightTrigger.onToggle(InstantCmd({ miyuki.hardware.arm.power = 0.07 }))

//        driver.leftTrigger.onPress(InstantCmd({ hw.pivot.position = PivotConstants.pivotHome }))
//        driver.rightTrigger.onPress(InstantCmd({ hw.pivot.position = PivotConstants.pivotDeposit }))

//        miyuki.lift.defaultCommand = LiftCmds.LiftOpenLoopCmd(miyuki.lift, 0.0)
        driver.leftTrigger.onPress(LiftCmds.LiftOpenLoopCmd(miyuki.lift, LiftConstants.testPower))
        driver.rightTrigger.onPress(LiftCmds.LiftOpenLoopCmd(miyuki.lift, -LiftConstants.testPower))
        driver.rightBumper.onPress(LiftCmds.LiftOpenLoopCmd(miyuki.lift, 0.0))
//        driver.leftBumper.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, 1.0))
//        driver.rightBumper.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, -1.0))
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

//        miyuki.drive.defaultCommand = MecanumCmd(
//            miyuki.drive,
//            driver.leftStick.yInverted,
//            driver.rightStick
//        )
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
//        Logger.addTelemetryData("arm", miyuki.hardware.arm.pos)
//        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
//        Logger.addTelemetryData("arm power", miyuki.hardware.arm.power)
    }
}
