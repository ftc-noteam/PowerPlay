package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.*
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoDepositSeq(miyuki: Miyuki, liftHeight: Double) : SequentialGroup(
    ClawDepositCmd(miyuki.claw)
        .andPause(1.0),
    ClawGripCmd(miyuki.claw)
        .andPause(1.0),
    ParallelGroup(
        PivotHomeCmd(miyuki.pivot),
        LiftCmd(miyuki.lift, liftHeight),
        ClawOpenCmd(miyuki.claw).waitUntil { miyuki.arm.pos < 90.0 },
        ArmPickupCmd(miyuki.arm)
    )
)