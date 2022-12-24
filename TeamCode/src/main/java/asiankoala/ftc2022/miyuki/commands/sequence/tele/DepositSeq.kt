package asiankoala.ftc2022.miyuki.commands.sequence.tele

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.State
import asiankoala.ftc2022.miyuki.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(
    miyuki: Miyuki,
) : SequentialGroup(
    InstantCmd({ miyuki.state = State.HOMING }),
    ClawDepositCmd(miyuki.claw)
        .andPause(0.6),
    ClawGripCmd(miyuki.claw)
        .andPause(0.3),
    ParallelGroup(
        PivotHomeCmd(miyuki.pivot),
        LiftHomeCmd(miyuki.lift),
        ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
        ArmPickupCmd(miyuki.arm)
    ),
//    WaitUntilCmd { miyuki.arm.pos < 45.0 },
//    ClawOpenCmd(miyuki.claw),
//    PivotHomeCmd(miyuki.pivot),
    InstantCmd({ miyuki.state = State.INTAKING })
)