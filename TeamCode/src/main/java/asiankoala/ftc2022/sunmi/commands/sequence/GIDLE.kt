package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class GIDLE(sunmi: Sunmi, b: () -> Boolean) : SequentialGroup(
    InstantCmd({ sunmi.state = State.INTAKING }),
    ChooseCmd(
        StackSeq(sunmi, b),
        SmartIntakeSeq(sunmi.claw, sunmi.arm),
        sunmi::isStacking
    ),
    SequentialGroup(
        Soyeon(sunmi).waitUntil(b),
        ClawOpenCmd(sunmi.claw).waitUntil(b),
        WaitCmd(0.5),
        IdleSeq(sunmi)
    )
)