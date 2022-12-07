package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.commands.sequence.auto.PreInitCmd
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class InitTest : TestableAuto() {
    override val mainCmd: Cmd by lazy { PreInitCmd(miyuki, driver.rightTrigger::isJustPressed) }
}