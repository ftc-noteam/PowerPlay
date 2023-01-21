package asiankoala.ftc2022.sunmi.commands.sequence.tele

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KButton

class GIDLE(sunmi: Sunmi, rt: KButton) : SequentialGroup(
    ArmCmd(sunmi.arm, ArmConstants.gidle),
    WaitCmd(1.0),
    ClawOpenCmd(sunmi.claw),
    WaitUntilCmd(rt::isJustPressed),
    ArmCmd(sunmi.arm, ArmConstants.intake),
    WaitCmd(1.0),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(1.0),
    ArmCmd(sunmi.arm, ArmConstants.transit),
    WaitCmd(1.0),
    PivotCmd(sunmi.pivot, PivotConstants.deposit),
    WaitUntilCmd(rt::isJustPressed),
    LiftStateCmd(sunmi.lift, sunmi.strategy),
    WaitUntilCmd(rt::isJustPressed),
    ArmCmd(sunmi.arm, ArmConstants.deposit),
    WaitUntilCmd(rt::isJustPressed),
    ClawOpenCmd(sunmi.claw),
    WaitCmd(1.0),
    IdleSeq(sunmi)
)