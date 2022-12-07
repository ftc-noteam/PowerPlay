package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.group.ParallelGroup

class AutoReadySeq(miyuki: Miyuki) : ParallelGroup(
    LiftHighCmd(miyuki.lift),
    ArmHighCmd(miyuki.arm),
    PivotDepositCmd(miyuki.pivot)
)