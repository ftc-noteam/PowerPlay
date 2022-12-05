package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
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
    miyuki: Miyuki,
    rightTriggerPressed: () -> Boolean
) : SequentialGroup(
    ClawCmds.ClawDepositCmd(miyuki.claw),
    WaitCmd(0.5),
    WaitUntilCmd(rightTriggerPressed),
    ClawCmds.ClawGripCmd(miyuki.claw),
    ParallelGroup(
        PivotCmds.PivotHomeCmd(miyuki.pivot),
        LiftCmds.LiftHomeCmd(miyuki.lift),
        ClawCmds.ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos > 90.0 },
        ArmCmds.ArmCmd(miyuki.arm, 0.0)
            .andThen(
                WaitCmd(1.0),
                ArmCmds.ArmPickupCmd(miyuki.arm)
            )
    ),
    InstantCmd({ miyuki.state = State.INTAKING })
)