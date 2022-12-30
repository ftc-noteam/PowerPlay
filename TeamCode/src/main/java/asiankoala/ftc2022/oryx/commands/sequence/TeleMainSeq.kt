package asiankoala.ftc2022.oryx.commands.sequence

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.RaceGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KButton
import com.asiankoala.koawalib.gamepad.KTrigger

class TeleMainSeq(
    oryx: Oryx,
    rt: KTrigger,
    lt: KTrigger,
) : RaceGroup(
    SequentialGroup(
        ChooseCmd(
            StackIntakeSeq(oryx, oryx.stackNum, rt),
            DefaultIntakeSeq(oryx)
        ) { oryx.isStacking },
        InstantCmd({ oryx.state = State.READYING }),
        ArmCmd(oryx.arm, 90.0)
            .waitUntil(rt::isJustPressed),
        ParallelGroup(
            LiftStateCmd(oryx.lift, oryx.strategy),
            ArmStateCmd(oryx.arm, oryx.strategy),
            WaitUntilCmd { oryx.arm.pos > 0.0 },
            PivotStateCmd(oryx.pivot, oryx.strategy)
        ).waitUntil(rt::isJustPressed),
        DepositSeq(oryx)
            .waitUntil(rt::isJustPressed)
    ),
    SequentialGroup(
        WaitUntilCmd(lt::isJustPressed),
        LiftHomeCmd(oryx.lift),
        InstantCmd({ oryx.state = State.INTAKING })
    )
)
