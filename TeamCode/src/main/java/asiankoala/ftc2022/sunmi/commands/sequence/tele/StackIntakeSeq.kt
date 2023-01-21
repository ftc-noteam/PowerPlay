package asiankoala.ftc2022.sunmi.commands.sequence.tele

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCloseCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KTrigger

class StackIntakeSeq(
    sunmi: Sunmi,
    n: Int,
    rt: KTrigger
) : SequentialGroup(
    LiftCmd(sunmi.lift, LiftConstants.stackPositions[n])
        .waitUntil(rt::isJustPressed),
    ClawCloseCmd(sunmi.claw)
        .andPause(0.3),
    LiftCmd(sunmi.lift, LiftConstants.stackPositions[n] + LiftConstants.stackSafeRaiseDelta),
)