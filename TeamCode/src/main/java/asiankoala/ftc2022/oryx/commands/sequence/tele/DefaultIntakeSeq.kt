package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftReadyCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class DefaultIntakeSeq(oryx: Oryx) : SequentialGroup(
    ClawGripCmd(oryx.claw)
        .andPause(0.3),
    LiftReadyCmd(oryx.lift),
)