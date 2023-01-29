package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import kotlin.math.max

class StackSeq(sunmi: Sunmi, b: () -> Boolean) : SequentialGroup(
    LiftCmd(sunmi.lift, sunmi.stackHeight),
    WaitUntilCmd(b),
    ArmCmd(sunmi.arm, ArmConstants.intake),
    WaitCmd(0.4),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(0.7),
    LiftCmd(sunmi.lift, 10.0),
    WaitCmd(0.5),
    PivotCmd(sunmi.pivot, PivotConstants.deposit),
    InstantCmd({ sunmi.stack = max(sunmi.stack - 1, 0)})
)