package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSeq(
    miyuki: Miyuki,
) : SequentialGroup(
    InstantCmd({ miyuki.state = State.HOMING }),
    ClawDepositCmd(miyuki.claw)
        .andPause(1.0),
    ClawGripCmd(miyuki.claw),
    WaitCmd(1.0),
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