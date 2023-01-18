package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftReadyCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class DefaultIntakeSeq(sunmi: Sunmi) : SequentialGroup(
    ClawGripCmd(sunmi.claw)
        .andPause(0.3),
    LiftReadyCmd(sunmi.lift),
)