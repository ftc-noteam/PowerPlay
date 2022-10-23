package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.CmdChooser
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class ReadySequence(
    miyuki: Miyuki
) : SequentialGroup(
    CmdChooser.triple(miyuki),
    InstantCmd(MiyukiState::nextState)
)