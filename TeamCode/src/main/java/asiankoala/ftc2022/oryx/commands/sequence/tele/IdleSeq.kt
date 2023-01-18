package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.ArmHomeCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftHomeCmd
import asiankoala.ftc2022.oryx.commands.subsystem.PivotHomeCmd
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class IdleSeq(sunmi: Sunmi) : ParallelGroup(
    InstantCmd({ sunmi.state = State.IDLE }),
    PivotHomeCmd(sunmi.pivot),
    LiftHomeCmd(sunmi.lift),
    ArmHomeCmd(sunmi.arm),
    InstantCmd({ sunmi.state = State.INTAKING }),
)
