package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.ArmHomeCmd
import asiankoala.ftc2022.oryx.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftHomeCmd
import asiankoala.ftc2022.oryx.commands.subsystem.PivotHomeCmd
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(oryx: Oryx) : SequentialGroup(
    InstantCmd({ oryx.state = State.HOMING }),
    ClawOpenCmd(oryx.claw)
        .andPause(0.4),
    ParallelGroup(
        PivotHomeCmd(oryx.pivot),
        LiftHomeCmd(oryx.lift),
        ArmHomeCmd(oryx.arm),
        InstantCmd({ oryx.state = State.INTAKING }),
    )
)
