package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.commands.sequence.auto.PreInitCmd
import com.asiankoala.koawalib.command.commands.Cmd
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class InitTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy { PreInitCmd(miyuki, driver.rightTrigger::isJustPressed) }
}