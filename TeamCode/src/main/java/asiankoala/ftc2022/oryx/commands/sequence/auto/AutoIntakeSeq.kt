package asiankoala.ftc2022.oryx.commands.sequence.auto

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
import asiankoala.ftc2022.oryx.Strategy
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

// assumes lift is at stack heigth
class AutoIntakeSeq(sunmi: Sunmi) : SequentialGroup(
    ClawGripCmd(sunmi.claw),
    WaitCmd(3.0),
    LiftCmd(sunmi.lift, LiftConstants.stackSafeRaiseHeight),
    WaitCmd(3.0),
    ArmStateCmd(sunmi.arm, Strategy.HIGH),
    WaitCmd(3.0),
    PivotStateCmd(sunmi.pivot, Strategy.HIGH),
)