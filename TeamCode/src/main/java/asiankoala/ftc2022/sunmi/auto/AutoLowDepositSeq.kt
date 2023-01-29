package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoLowDepositSeq(sunmi: Sunmi, b: () -> Boolean) : SequentialGroup(
    LiftCmd(sunmi.lift, LiftConstants.lowAutoDepositDown),
    WaitUntilCmd(b),
    ClawOpenCmd(sunmi.claw),
    WaitUntilCmd(b),
    LiftCmd(sunmi.lift, LiftConstants.lowAutoDepositUp),
    WaitUntilCmd(b),
    IdleSeq(sunmi),
    WaitUntilCmd(b),
)