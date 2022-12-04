package asiankoala.ftc2022.commands.sequence.teleop

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class ReadySequence(
    miyuki: Miyuki,
) : ParallelGroup(
    miyuki.liftCmd,
    miyuki.armCmd,
    WaitUntilCmd { miyuki.arm.pos > 0.0 }
        .andThen(PivotCmds.PivotDepositCmd(miyuki.pivot))
)