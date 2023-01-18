package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.ClawOpenCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(sunmi: Sunmi) : SequentialGroup(
    ClawOpenCmd(sunmi.claw)
        .andPause(0.4),
)
