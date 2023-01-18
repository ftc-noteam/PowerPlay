package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftCmd
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KTrigger

class StackIntakeSeq(
    sunmi: Sunmi,
    n: Int,
    rt: KTrigger
) : SequentialGroup(
    LiftCmd(sunmi.lift, LiftConstants.stackPositions[n])
        .waitUntil(rt::isJustPressed),
    ClawGripCmd(sunmi.claw)
        .andPause(0.3),
    LiftCmd(sunmi.lift, LiftConstants.stackPositions[n] + LiftConstants.stackSafeRaiseDelta),
)