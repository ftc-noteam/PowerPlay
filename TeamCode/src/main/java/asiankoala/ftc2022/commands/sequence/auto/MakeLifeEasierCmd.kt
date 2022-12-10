package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.commands.subsystem.PivotCmd
import asiankoala.ftc2022.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose

class MakeLifeEasierCmd(miyuki: Miyuki, rightTrigger: () -> Boolean, startPose: Pose) : SequentialGroup(
    ClawGripCmd(miyuki.claw)
        .waitUntil(rightTrigger),
    PivotCmd(miyuki.pivot, PivotConstants.FUCK)
        .waitUntil(rightTrigger),
    ResetPoseCmd(miyuki, startPose)
        .waitUntil(rightTrigger)
)