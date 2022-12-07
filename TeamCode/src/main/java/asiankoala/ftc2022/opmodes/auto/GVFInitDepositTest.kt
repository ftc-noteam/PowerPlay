package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class GVFInitDepositTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        getGVFCmd(
            miyuki,
            initPath,
        )
    }
}