package asiankoala.ftc2022.sunmi.commands.sequence.tele

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class GIDLE(sunmi: Sunmi, b: () -> Boolean) : SequentialGroup(
    InstantCmd({ sunmi.state = State.INTAKING }),
    ChooseCmd(
        SequentialGroup(
            LiftCmd(sunmi.lift, sunmi.stack * 1.0),
            WaitUntilCmd(b),
            ArmCmd(sunmi.arm, ArmConstants.intake),
            WaitCmd(0.4),
            ClawCloseCmd(sunmi.claw),
            WaitCmd(0.7),
            LiftCmd(sunmi.lift, 10.0),
            WaitCmd(1.5),
            ArmCmd(sunmi.arm, ArmConstants.deposit),
            PivotCmd(sunmi.pivot, PivotConstants.deposit)
        ),
        SequentialGroup(
            ArmCmd(sunmi.arm, ArmConstants.intake),
            WaitCmd(0.4),
            ClawCloseCmd(sunmi.claw),
            WaitCmd(0.7),
            ArmCmd(sunmi.arm, ArmConstants.deposit),
            PivotCmd(sunmi.pivot, PivotConstants.deposit),
        ),
        sunmi::isStacking
    ),
    SequentialGroup(
        Soyeon(sunmi)
            .waitUntil(b),
        ClawOpenCmd(sunmi.claw)
            .waitUntil(b),
        WaitCmd(1.0),
        IdleSeq(sunmi)
    )
)