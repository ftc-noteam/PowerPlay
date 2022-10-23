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
    cmdChooser: CmdChooser,
    lift: Lift,
    arm: Arm,
    pivot: Pivot,
    claw: Claw,
) : SequentialGroup(
    WaitUntilCmd(claw::readyToGrab),
    ClawCmds.ClawGripCmd(claw),
    WaitCmd(0.2),
    cmdChooser.liftCmd(lift)
        .alongWith(cmdChooser.armCmd(arm)),
    WaitCmd(0.2),
    cmdChooser.pivotCmd(pivot),
    InstantCmd(MiyukiState::nextState)
)
