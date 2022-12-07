package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoDepositSeq(miyuki: Miyuki) : SequentialGroup(
    ClawDepositCmd(miyuki.claw)
        .andPause(0.5),
    ClawGripCmd(miyuki.claw),
    ParallelGroup(
        PivotHomeCmd(miyuki.pivot),
        LiftHomeCmd(miyuki.lift),
        ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
        ArmCmd(miyuki.arm, 0.0)
            .andThen(
                WaitCmd(1.0),
                ArmPickupCmd(miyuki.arm)
            )
    )
)