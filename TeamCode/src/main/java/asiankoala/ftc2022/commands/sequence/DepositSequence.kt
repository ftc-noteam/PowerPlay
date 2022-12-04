package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSequence(
    miyuki: Miyuki
) : SequentialGroup(
    ClawCmds.ClawDepositCmd(miyuki.claw),
    WaitCmd(0.5),
    ParallelGroup(
        ClawCmds.ClawGripCmd(miyuki.claw),
        PivotCmds.PivotHomeCmd(miyuki.pivot),
        ArmCmds.ArmHomeCmd(miyuki.arm),
    ),
    WaitUntilCmd { miyuki.arm.pos < 90.0 },
    ParallelGroup(
        LiftCmds.LiftHomeCmd(miyuki.lift),
        ClawCmds.ClawOpenCmd(miyuki.claw)
    )
)
