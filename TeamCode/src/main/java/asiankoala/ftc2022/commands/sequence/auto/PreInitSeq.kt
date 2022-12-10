package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ArmCmd
import asiankoala.ftc2022.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.commands.subsystem.PivotDepositCmd
import asiankoala.ftc2022.subsystems.constants.ArmConstants
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
    ResetPoseCmd(miyuki, startPose)
        .waitUntil(rightTriggerIsJustPressed),
)
