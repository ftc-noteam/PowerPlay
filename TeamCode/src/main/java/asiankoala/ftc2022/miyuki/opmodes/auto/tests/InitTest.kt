package asiankoala.ftc2022.miyuki.opmodes.auto.tests

import asiankoala.ftc2022.miyuki.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.miyuki.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.util.OpModeState

class InitTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, Pose())
            .andThen(WaitUntilCmd { opModeState == OpModeState.START })
    }
}