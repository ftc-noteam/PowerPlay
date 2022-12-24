package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.ArmHighCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.LiftHighCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.PivotDepositCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class AutoReadySeq(miyuki: Miyuki) : ParallelGroup(
    LiftHighCmd(miyuki.lift),
    ArmHighCmd(miyuki.arm),
    WaitUntilCmd { miyuki.arm.pos > 0.0 }
        .andThen(PivotDepositCmd(miyuki.pivot))
)