package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Strategy
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSequence(
    private val strategy: Strategy,
    private val claw: Claw,
    private val lift: Lift,
    private val arm: Arm,
) : SequentialGroup(
    WaitUntilCmd(claw::readyToGrab),
    ClawCmds.ClawGripCmd(claw),
    WaitCmd(0.3),
)