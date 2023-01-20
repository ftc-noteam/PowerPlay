package asiankoala.ftc2022.oryx.commands.sequence.tele

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.State
import asiankoala.ftc2022.oryx.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.RaceGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KTrigger

class TeleMainSeq(
    sunmi: Sunmi,
    rt: KTrigger,
    lt: KTrigger,
) : SequentialGroup(
    RaceGroup(
        SequentialGroup(
//            ChooseCmd(
//                StackIntakeSeq(sunmi, sunmi.stackNum, rt),
//                DefaultIntakeSeq(sunmi)
//            ) { sunmi.isStacking },
            DefaultIntakeSeq(sunmi),
            InstantCmd({ sunmi.state = State.READYING }),
            ArmCmd(sunmi.arm, ArmConstants.transit)
                .waitUntil(rt::isJustPressed),
            ParallelGroup(
                LiftStateCmd(sunmi.lift, sunmi.strategy),
                ArmStateCmd(sunmi.arm, sunmi.strategy),
                PivotStateCmd(sunmi.pivot, sunmi.strategy)
            ).waitUntil(rt::isJustPressed),
            DepositSeq(sunmi)
                .waitUntil(rt::isJustPressed)
        ),
        SequentialGroup(
            WaitUntilCmd(lt::isJustPressed),
            InstantCmd({ sunmi.state = State.INTAKING })
        ),
    ),
    IdleSeq(sunmi),
)
