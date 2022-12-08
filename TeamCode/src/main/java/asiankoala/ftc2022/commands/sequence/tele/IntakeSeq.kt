package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.State
import asiankoala.ftc2022.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.commands.subsystem.LiftReadyCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSeq(
    miyuki: Miyuki,
//    leftTriggerPressed: () -> Boolean
) : SequentialGroup(
    ClawGripCmd(miyuki.claw)
        .andPause(0.5),
    LiftReadyCmd(miyuki.lift),
    InstantCmd({ miyuki.state = State.READYING }),
    ReadySeq(miyuki),
//        .waitUntil(leftTriggerPressed),
    InstantCmd({ miyuki.state = State.DEPOSITING })
)
