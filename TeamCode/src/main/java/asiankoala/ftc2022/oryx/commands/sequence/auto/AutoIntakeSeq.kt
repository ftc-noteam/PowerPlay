package asiankoala.ftc2022.oryx.commands.sequence.auto

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.commands.subsystem.*
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

// assumes lift is at stack heigth
class AutoIntakeSeq(oryx: Oryx) : SequentialGroup(
    ClawGripCmd(oryx.claw),
    WaitCmd(3.0),
    LiftCmd(oryx.lift, LiftConstants.stackSafeRaiseHeight),
    WaitCmd(3.0),
    ArmStateCmd(oryx.arm, Strategy.HIGH),
    WaitCmd(3.0),
    PivotStateCmd(oryx.pivot, Strategy.HIGH),
)