package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCloseCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

// assumes a LiftCmd(sunmi.lift, sunmi.stackHeight) has been run before this
class AutoIntakeSeq(sunmi: Sunmi) : SequentialGroup(
    WaitCmd(0.3),
    ArmCmd(sunmi.arm, ArmConstants.intake),
    WaitCmd(0.4),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(0.4),
    LiftCmd(sunmi.lift, sunmi.stackHeight + 6.0),
    WaitCmd(0.4),
    ArmCmd(sunmi.arm, ArmConstants.deposit),
)