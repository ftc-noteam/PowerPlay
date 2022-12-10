package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class AutoReadySeq(miyuki: Miyuki) : ParallelGroup(
    LiftHighCmd(miyuki.lift),
    ArmHighCmd(miyuki.arm),
    WaitUntilCmd { miyuki.arm.pos > 0.0 && miyuki.arm.pos < 180.0 }
        .andThen(PivotDepositCmd(miyuki.pivot))
)