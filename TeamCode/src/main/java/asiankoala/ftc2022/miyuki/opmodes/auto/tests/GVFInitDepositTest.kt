package asiankoala.ftc2022.miyuki.opmodes.auto.tests

import asiankoala.ftc2022.miyuki.commands.sequence.auto.MakeLifeEasierCmd
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants.depositPath
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants.getGVFCmd
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants.initPath
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants.initIntakePath
import asiankoala.ftc2022.miyuki.opmodes.auto.AutoConstants.intakePath
import asiankoala.ftc2022.miyuki.opmodes.auto.SimpleAuto
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled

@Autonomous
@Disabled
class GVFInitDepositTest : SimpleAuto() {
    override val mainCmd: Cmd by lazy {
        SequentialGroup(
//            PreInitSeq(miyuki, driver.rightTrigger::isJustPressed, AutoConstants.startPose),
            MakeLifeEasierCmd(miyuki, driver.rightTrigger::isJustPressed, AutoConstants.startPose),
            WaitUntilCmd { opModeState == OpModeState.START },
            getGVFCmd(
                miyuki,
                initPath,
            ).andPause(0.5),
            getGVFCmd(
                miyuki,
                initIntakePath
            ).andPause(0.5),
            getGVFCmd(
                miyuki,
                depositPath
            ).andPause(0.5),
            getGVFCmd(
                miyuki,
                intakePath
            )
        )
    }
}