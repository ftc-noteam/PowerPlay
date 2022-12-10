package asiankoala.ftc2022.opmodes.auto.tests

import asiankoala.ftc2022.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

class InitTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, Pose())
            .andThen(WaitUntilCmd { opModeState == OpModeState.START })
    }
}