package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.LiftCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoIntakeSeq(miyuki: Miyuki, liftHeight: Double) : SequentialGroup(
    ClawGripCmd(miyuki.claw)
        .andPause(0.5),
    LiftCmd(miyuki.lift, liftHeight)
)