package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.group.ParallelGroup

class SoyeonAuto(sunmi: Sunmi) : ParallelGroup(
    LiftCmd(sunmi.lift, LiftConstants.high - 0.2),
    sunmi.armCmd,
    sunmi.pivotCmd
)
