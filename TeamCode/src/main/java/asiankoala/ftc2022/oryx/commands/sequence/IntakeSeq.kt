package asiankoala.ftc2022.oryx.commands.sequence

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
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
        ArmCmd(oryx.arm, 90.0)
            .waitUntil(rt::isJustPressed),
        ParallelGroup(
            LiftStateCmd(oryx.lift, oryx.strategy),
            ArmStateCmd(oryx.arm, oryx.strategy),
            WaitUntilCmd { oryx.arm.pos > 0.0 },
            PivotStateCmd(oryx.pivot, oryx.strategy)
        ).waitUntil(rt::isJustPressed)
    ),
    SequentialGroup(
        WaitUntilCmd(lb::isJustPressed),
        LiftHomeCmd(oryx.lift),
        InstantCmd({ oryx.state = State.INTAKING })
    )
)
