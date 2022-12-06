package asiankoala.ftc2022.commands.sequence.teleop

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(
    miyuki: Miyuki,
    rightTriggerPressed: () -> Boolean
) : SequentialGroup(
    ClawCmds.ClawDepositCmd(miyuki.claw),
    InstantCmd({ miyuki.state = State.HOMING }),
    ClawCmds.ClawGripCmd(miyuki.claw),
//        .waitUntil(rightTriggerPressed),
    ParallelGroup(
        PivotCmds.PivotHomeCmd(miyuki.pivot),
        LiftCmds.LiftHomeCmd(miyuki.lift),
        ClawCmds.ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
        ArmCmds.ArmPickupCmd(miyuki.arm)
    ),
    InstantCmd({ miyuki.state = State.INTAKING })
)