package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class ReadySequence(
    miyuki: Miyuki,
) : ParallelGroup(
    LiftCmds.LiftMedCmd(miyuki.lift),
    ArmCmds.ArmDepositCmd(miyuki.arm),
    WaitUntilCmd { miyuki.arm.pos > 0.0 }
        .andThen(PivotCmds.PivotDepositCmd(miyuki.pivot))
)