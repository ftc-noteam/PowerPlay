package asiankoala.ftc2022.opmodes.auto.tests

import asiankoala.ftc2022.commands.sequence.auto.MakeLifeEasierCmd
import asiankoala.ftc2022.commands.sequence.auto.PreInitSeq
import asiankoala.ftc2022.opmodes.auto.AutoConstants
import asiankoala.ftc2022.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class GVFInitDepositTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        SequentialGroup(
//            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, AutoConstants.startPose),
            MakeLifeEasierCmd(miyuki, driver.rightTrigger::isJustPressed, AutoConstants.startPose),
            WaitUntilCmd { opModeState == OpModeState.START },
            getGVFCmd(
                miyuki,
                initPath
            )
        )
    }
}