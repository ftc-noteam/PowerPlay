package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.group.SequentialGroup

class AutoDepoReadyCmd(sunmi: Sunmi) : SequentialGroup(
    ArmCmd(sunmi.arm, ArmConstants.deposit),
    LiftCmd(sunmi.lift, LiftConstants.high),
    PivotCmd(sunmi.pivot, PivotConstants.deposit)
)