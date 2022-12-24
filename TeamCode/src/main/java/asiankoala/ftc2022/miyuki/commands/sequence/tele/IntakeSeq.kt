package asiankoala.ftc2022.miyuki.commands.sequence.tele

import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.State
import asiankoala.ftc2022.miyuki.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.LiftHomeCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.LiftReadyCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.RaceGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class IntakeSeq(
    miyuki: Miyuki,
    rightTriggerPressed: () -> Boolean,
    leftBumper: () -> Boolean,
    leftTrigger: () -> Boolean
) : RaceGroup(
    SequentialGroup(
        ClawGripCmd(miyuki.claw)
            .andPause(0.5),
        LiftReadyCmd(miyuki.lift),
        InstantCmd({ miyuki.state = State.READYING }),
        ReadySeq(miyuki)
            .waitUntil(rightTriggerPressed),
        InstantCmd({ miyuki.state = State.DEPOSITING }),
        WaitUntilCmd(leftTrigger)
    ),
    SequentialGroup(
        WaitUntilCmd(leftBumper),
        LiftHomeCmd(miyuki.lift),
        InstantCmd({ miyuki.state = State.INTAKING })
    )
)
