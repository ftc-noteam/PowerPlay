package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCloseCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

// assumes a LiftCmd(sunmi.lift, sunmi.stackHeight) has been run before this
class AutoIntakeSeq(sunmi: Sunmi) : SequentialGroup(
    ArmCmd(sunmi.arm, ArmConstants.intake),
    WaitCmd(3.0),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(3.0),
    LiftCmd(sunmi.lift, 10.0),
    WaitCmd(3.0),
    ArmCmd(sunmi.arm, ArmConstants.home),
)