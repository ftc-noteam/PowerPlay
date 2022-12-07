package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.commands.subsystem.LiftCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoIntakeSeq(miyuki: Miyuki, liftHeight: Double) : SequentialGroup(
    ClawGripCmd(miyuki.claw)
        .andPause(0.5),
    LiftCmd(miyuki.lift, liftHeight)
)