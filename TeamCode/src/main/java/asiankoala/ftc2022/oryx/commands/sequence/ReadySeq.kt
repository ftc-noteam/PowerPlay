package asiankoala.ftc2022.oryx.commands.sequence

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.ArmStateCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftStateCmd
import asiankoala.ftc2022.oryx.commands.subsystem.PivotStateCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class ReadySeq(
    oryx: Oryx
) : ParallelGroup(
    LiftStateCmd(oryx.lift, oryx.strategy),
    ArmStateCmd(oryx.arm, oryx.strategy),
    WaitUntilCmd { oryx.arm.pos > 0.0 },
    PivotStateCmd(oryx.pivot, oryx.strategy)
)