package asiankoala.ftc2022.opmodes.auto.tests

import asiankoala.ftc2022.opmodes.auto.AutoConstants.depositPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.opmodes.auto.AutoConstants.intakePath
import asiankoala.ftc2022.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class GVFInitAndCycleTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        SequentialGroup(
            getGVFCmd(miyuki, initPath)
                .andPause(1.0),
            getGVFCmd(miyuki, intakePath)
                .andPause(1.0),
            getGVFCmd(miyuki, depositPath)
        )
    }
}