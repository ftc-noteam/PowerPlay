package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSequence(
    miyuki: Miyuki
) : SequentialGroup(
    ClawCmds.ClawDepositCmd(miyuki.claw),
    CmdChooser.homeCmd(miyuki),
    InstantCmd(MiyukiState::nextState)
)
