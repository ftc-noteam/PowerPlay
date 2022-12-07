package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.*
import com.asiankoala.koawalib.command.group.SequentialGroup

class HomeSeq(miyuki: Miyuki) : SequentialGroup(
    ClawGripCmd(miyuki.claw),
    LiftHomeCmd(miyuki.lift),
    ArmPickupCmd(miyuki.arm),
    PivotHomeCmd(miyuki.pivot),
    ClawOpenCmd(miyuki.claw)
        .waitUntil { miyuki.arm.pos < 90.0 }
)