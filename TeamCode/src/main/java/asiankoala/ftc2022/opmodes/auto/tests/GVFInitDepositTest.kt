package asiankoala.ftc2022.opmodes.auto.tests

import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
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