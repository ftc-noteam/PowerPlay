package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.subsystems.Claw
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSequence(
    claw: Claw,
) : SequentialGroup(
    ClawCmds.ClawGripCmd(claw),
//    InstantCmd(MiyukiState::nextState)
)
