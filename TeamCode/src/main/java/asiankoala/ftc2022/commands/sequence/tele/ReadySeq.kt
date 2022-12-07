package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.PivotDepositCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class ReadySeq(
    miyuki: Miyuki,
) : ParallelGroup(
    miyuki.liftCmd,
    miyuki.armCmd,
    WaitUntilCmd { miyuki.arm.pos > 0.0 }
        .andThen(PivotDepositCmd(miyuki.pivot))
)