package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSequence(
    claw: Claw,
) : SequentialGroup(
    WaitUntilCmd(claw::readyToGrab),
    ClawCmds.ClawGripCmd(claw),
    InstantCmd(MiyukiState::nextState)
)
