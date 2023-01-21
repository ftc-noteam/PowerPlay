package asiankoala.ftc2022.sunmi.commands.sequence.tele

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class IdleSeq(sunmi: Sunmi) : ParallelGroup(
    InstantCmd({ sunmi.state = State.IDLE }),
    ArmCmd(sunmi.arm, ArmConstants.gidle),
    LiftCmd(sunmi.lift, LiftConstants.homeAfterIntaking),
    PivotHomeCmd(sunmi.pivot),
    ClawOpenCmd(sunmi.claw)
)
