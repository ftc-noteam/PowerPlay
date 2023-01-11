package asiankoala.ftc2022.oryx.commands.sequence.auto

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoDepositSeq(oryx: Oryx) : SequentialGroup(
    ClawOpenCmd(oryx.claw),
    WaitCmd(2.0),
    ClawGripCmd(oryx.claw),
    WaitCmd(2.0),
    ParallelGroup(
        LiftCmd(oryx.lift, oryx.stackLiftHeight),
        ArmHomeCmd(oryx.arm),
        PivotHomeCmd(oryx.pivot),
    )
)