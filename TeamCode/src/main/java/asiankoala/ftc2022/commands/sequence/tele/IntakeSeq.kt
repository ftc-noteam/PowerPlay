package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSeq(
    miyuki: Miyuki,
    leftTriggerPressed: () -> Boolean
) : SequentialGroup(
    ClawCmds.ClawGripCmd(miyuki.claw)
        .andPause(0.5),
    LiftCmds.LiftReadyCmd(miyuki.lift),
    InstantCmd({ miyuki.state = State.READYING }),
    ReadySeq(miyuki)
        .waitUntil(leftTriggerPressed),
    InstantCmd({ miyuki.state = State.DEPOSITING })
)
