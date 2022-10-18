package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.gamepad.KTrigger

class IntakeSequence(
    cmdChooser: CmdChooser,
    lift: Lift,
    arm: Arm,
    pivot: Pivot,
    claw: Claw,
    trigger: KTrigger
) : SequentialGroup(
    WaitUntilCmd(claw::readyToGrab),
    ClawCmds.ClawGripCmd(claw),
    WaitUntilCmd(trigger::isJustPressed),
    ParallelGroup(
        cmdChooser.liftCmd(lift),
        cmdChooser.armCmd(arm),
        cmdChooser.pivotCmd(pivot)
    ),
    WaitUntilCmd(trigger::isJustPressed),
    ClawCmds.ClawDepositCmd(claw)
)
