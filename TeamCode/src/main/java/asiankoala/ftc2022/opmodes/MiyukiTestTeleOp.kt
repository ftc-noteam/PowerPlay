package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.sequence.DepositSequence
import asiankoala.ftc2022.commands.sequence.IntakeSequence
import asiankoala.ftc2022.commands.sequence.ReadySequence
import asiankoala.ftc2022.commands.subsystem.ArmCmds.ArmCmd
import asiankoala.ftc2022.commands.subsystem.LiftCmds.LiftCmd
import asiankoala.ftc2022.subsystems.ArmConstants
import asiankoala.ftc2022.subsystems.ClawConstants
import asiankoala.ftc2022.subsystems.LiftConstants
import asiankoala.ftc2022.subsystems.PivotConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class MiyukiTestTeleOp : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        miyuki.hardware.odometry.unregister()
        scheduleDrive()
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
//        driver.leftTrigger.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, ArmConstants.testPower))
//        driver.rightTrigger.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, -ArmConstants.testPower))
//        val zeroCmd = ArmCmds.ArmOpenLoopCmd(miyuki.arm, 0.0)
//        driver.leftTrigger.onRelease(zeroCmd)
//        driver.rightTrigger.onRelease(zeroCmd)
//        driver.rightBumper.onPress(zeroCmd)
//        driver.leftBumper.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, 1.0))
//        driver.rightBumper.onPress(ArmCmds.ArmOpenLoopCmd(miyuki.arm, -1.0))
//        driver.leftBumper.onPress(InstantCmd({ miyuki.hardware.pivot.position = PivotConstants.home }))
//        driver.rightBumper.onPress(InstantCmd({ miyuki.hardware.pivot.position = PivotConstants.deposit }))
//        driver.leftTrigger.onPress(InstantCmd({ miyuki.hardware.claw.position = ClawConstants.openPos }))
//        driver.rightTrigger.onPress(InstantCmd({ miyuki.hardware.claw.position = ClawConstants.gripPos }))
//        driver.x.onPress(ArmCmd(miyuki.arm, ArmConstants.deposit))
//        driver.b.onPress(ArmCmd(miyuki.arm, 0.0))
//        driver.y.onPress(LiftCmd(miyuki.lift, LiftConstants.testPos))
//        driver.a.onPress(LiftCmd(miyuki.lift, LiftConstants.home))
        driver.rightBumper.onPress(IntakeSequence(miyuki.claw))
        driver.leftTrigger.onPress(ReadySequence(miyuki))
        driver.rightTrigger.onPress(DepositSequence(miyuki))
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

        miyuki.drive.defaultCommand = MecanumCmd(
            miyuki.drive,
            driver.leftStick,
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
        Logger.addTelemetryData("arm", miyuki.hardware.arm.pos)
        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
    }
}
