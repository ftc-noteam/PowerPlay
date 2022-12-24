package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.ArmCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.ClawCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.PivotCmd
import asiankoala.ftc2022.miyuki.subsystems.constants.ArmConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.ClawConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose

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
