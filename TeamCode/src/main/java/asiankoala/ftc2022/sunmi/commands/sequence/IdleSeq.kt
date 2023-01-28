package asiankoala.ftc2022.sunmi.commands.sequence

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
    SequentialGroup(
        LiftCmd(sunmi.lift, -0.6),
        WaitCmd(0.5),
        InstantCmd(sunmi.lift::startAttemptingZero)
    ),
    InstantCmd({ sunmi.state = State.IDLE }),
    ArmCmd(sunmi.arm, ArmConstants.gidle),
    PivotHomeCmd(sunmi.pivot),
    ClawCloseCmd(sunmi.claw),
    WaitCmd(1.0).andThen(ClawOpenCmd(sunmi.claw))
)
