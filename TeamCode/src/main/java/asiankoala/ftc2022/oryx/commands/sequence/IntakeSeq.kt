package asiankoala.ftc2022.oryx.commands.sequence

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftHomeCmd
import asiankoala.ftc2022.oryx.commands.subsystem.LiftReadyCmd
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.RaceGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KButton
import com.asiankoala.koawalib.gamepad.KTrigger

class IntakeSeq(
    oryx: Oryx,
    rt: KTrigger,
    lb: KButton,
) : RaceGroup(
    SequentialGroup(
        ClawGripCmd(oryx.claw)
            .andPause(0.3),
        LiftReadyCmd(oryx.lift),
        InstantCmd({ oryx.state = State.READYING }),
        ReadySeq(oryx)
            .waitUntil(rt::isJustPressed)
    ),
    SequentialGroup(
        WaitUntilCmd(lb::isJustPressed),
        LiftHomeCmd(oryx.lift),
        InstantCmd({ oryx.state = State.INTAKING })
    )
)
