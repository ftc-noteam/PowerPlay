package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(
    miyuki: Miyuki,
    rightTriggerPressed: () -> Boolean
) : SequentialGroup(
    ClawDepositCmd(miyuki.claw),
    InstantCmd({ miyuki.state = State.HOMING }),
    ClawGripCmd(miyuki.claw),
//        .waitUntil(rightTriggerPressed),
    ParallelGroup(
        PivotHomeCmd(miyuki.pivot),
        LiftHomeCmd(miyuki.lift),
        ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
        ArmPickupCmd(miyuki.arm)
    ),
    InstantCmd({ miyuki.state = State.INTAKING })
)