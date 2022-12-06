package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.group.ParallelGroup

class AutoReadySeq(miyuki: Miyuki) : ParallelGroup(
    LiftCmds.LiftHighCmd(miyuki.lift),
    ArmCmds.ArmHighCmd(miyuki.arm),
    PivotCmds.PivotDepositCmd(miyuki.pivot)
)