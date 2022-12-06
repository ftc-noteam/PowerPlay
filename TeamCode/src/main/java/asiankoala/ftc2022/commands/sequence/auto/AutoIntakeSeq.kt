package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoIntakeSeq(miyuki: Miyuki, liftHeight: Double) : SequentialGroup(
    ClawCmds.ClawGripCmd(miyuki.claw)
        .andPause(0.5),
    LiftCmds.LiftCmd(miyuki.lift, liftHeight)
)