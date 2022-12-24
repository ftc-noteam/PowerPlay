package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.PivotCmd
import asiankoala.ftc2022.miyuki.subsystems.constants.PivotConstants
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