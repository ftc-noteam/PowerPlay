package asiankoala.ftc2022.oryx.commands.sequence

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftCmd
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KTrigger

class StackIntakeSeq(
    oryx: Oryx,
    n: Int,
    rt: KTrigger
) : SequentialGroup(
    LiftCmd(oryx.lift, LiftConstants.stackPositions[n])
        .waitUntil(rt::isJustPressed),
    ClawGripCmd(oryx.claw)
        .andPause(0.3),
    LiftCmd(oryx.lift, LiftConstants.stackPositions[n] + LiftConstants.stackSafeRaiseDelta),
)