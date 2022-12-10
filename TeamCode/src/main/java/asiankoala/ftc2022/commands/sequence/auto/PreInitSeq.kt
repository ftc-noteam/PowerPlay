package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.*
import asiankoala.ftc2022.opmodes.auto.AutoConstants
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import asiankoala.ftc2022.subsystems.constants.ClawConstants
import asiankoala.ftc2022.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.util.OpModeState

class PreInitSeq(
    miyuki: Miyuki,
    rightTriggerIsJustPressed: () -> Boolean,
    startPose: Pose
) : SequentialGroup(
    ClawGripCmd(miyuki.claw)
        .waitUntil(rightTriggerIsJustPressed),
    ArmCmd(miyuki.arm, ArmConstants.autoInit)
        .waitUntil(rightTriggerIsJustPressed),
    ClawCmd(miyuki.claw, ClawConstants.autoOpen)
        .waitUntil(rightTriggerIsJustPressed),
    PivotCmd(miyuki.pivot, PivotConstants.autoInit),
    ClawGripCmd(miyuki.claw)
        .waitUntil(rightTriggerIsJustPressed),
    ResetPoseCmd(miyuki, startPose)
        .waitUntil(rightTriggerIsJustPressed),
)
