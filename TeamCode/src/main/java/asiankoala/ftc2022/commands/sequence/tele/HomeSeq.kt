package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import com.asiankoala.koawalib.command.group.SequentialGroup

class HomeSeq(miyuki: Miyuki) : SequentialGroup(
    ClawCmds.ClawGripCmd(miyuki.claw),
    LiftCmds.LiftHomeCmd(miyuki.lift),
    ArmCmds.ArmPickupCmd(miyuki.arm),
    PivotCmds.PivotHomeCmd(miyuki.pivot),
    ClawCmds.ClawOpenCmd(miyuki.claw)
        .waitUntil { miyuki.arm.pos < 90.0 }
)