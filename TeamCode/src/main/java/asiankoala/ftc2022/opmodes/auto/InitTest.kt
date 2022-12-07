package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class InitTest : TestableAuto() {
    override val mainCmd: Cmd by lazy {
        SequentialGroup(
        ArmCmds.ArmCmd(miyuki.arm, ArmConstants.autoInit)
            .waitUntil(driver.rightTrigger::isJustPressed),
        ClawCmds.ClawOpenCmd(miyuki.claw),
        ClawCmds.ClawGripCmd(miyuki.claw)
            .waitUntil(driver.rightTrigger::isJustPressed)
        )
    }
}