package asiankoala.ftc2022.commands.sequence

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class DepositSequence(
    lift: Lift,
    arm: Arm,
    pivot: Pivot,
    claw: Claw,
) : SequentialGroup(
    ClawCmds.ClawDepositCmd(claw),
    ParallelGroup(
        LiftCmds.LiftHomeCmd(lift),
        ArmCmds.ArmHomeCmd(arm),
        PivotCmds.PivotHomeCmd(pivot)
    ),
    WaitUntilCmd { arm.motor.isAtTarget() && lift.leadMotor.isAtTarget() },
    InstantCmd(MiyukiState::nextState)
)
