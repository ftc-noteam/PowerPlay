package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawOpenCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class JustDepositSeq(sunmi: Sunmi) : SequentialGroup(
    ClawOpenCmd(sunmi.claw),
    WaitCmd(0.5),
    IdleSeq(sunmi),
)