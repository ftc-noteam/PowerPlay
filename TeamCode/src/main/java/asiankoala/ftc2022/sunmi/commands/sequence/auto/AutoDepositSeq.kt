package asiankoala.ftc2022.sunmi.commands.sequence.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoDepositSeq(sunmi: Sunmi) : SequentialGroup(
    ClawOpenCmd(sunmi.claw),
    WaitCmd(2.0),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(2.0),
    ParallelGroup(
        LiftCmd(sunmi.lift, sunmi.stackLiftHeight),
        ArmHomeCmd(sunmi.arm),
        PivotHomeCmd(sunmi.pivot),
    )
)