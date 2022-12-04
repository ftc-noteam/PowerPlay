package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.subsystems.Claw
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSequence(
    miyuki: Miyuki
) : SequentialGroup(
    ClawCmds.ClawGripCmd(miyuki.claw),
    WaitCmd(0.5),
    LiftCmds.LiftMedCmd(miyuki.lift)
//    InstantCmd(MiyukiState::nextState)
)
