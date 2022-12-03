package asiankoala.ftc2022.commands

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.command.group.ParallelGroup

object CmdChooser {
    fun liftCmd(lift: Lift): LiftCmds.LiftCmd {
        return when (MiyukiState.strategy) {
            MiyukiState.DepositState.HIGH -> LiftCmds.LiftHighCmd(lift)
            MiyukiState.DepositState.MEDIUM -> LiftCmds.LiftMedCmd(lift)
            MiyukiState.DepositState.LOW -> LiftCmds.LiftLowCmd(lift)
            MiyukiState.DepositState.GROUND -> LiftCmds.LiftGroundCmd(lift)
        }
    }

//    fun armCmd(arm: Arm): ArmCmds.ArmCmd {
//        return when (MiyukiState.strategy) {
//            MiyukiState.DepositState.HIGH -> ArmCmds.ArmHighCmd(arm)
//            MiyukiState.DepositState.MEDIUM -> ArmCmds.ArmMedCmd(arm)
//            MiyukiState.DepositState.LOW -> ArmCmds.ArmLowCmd(arm)
//            MiyukiState.DepositState.GROUND -> ArmCmds.ArmGroundCmd(arm)
//        }
//    }
//
//    fun pivotCmd(pivot: Pivot): PivotCmds.PivotCmd {
//        return when (MiyukiState.strategy) {
//            MiyukiState.DepositState.HIGH -> PivotCmds.PivotHighCmd(pivot)
//            MiyukiState.DepositState.MEDIUM -> PivotCmds.PivotMedCmd(pivot)
//            MiyukiState.DepositState.LOW -> PivotCmds.PivotLowCmd(pivot)
//            MiyukiState.DepositState.GROUND -> PivotCmds.PivotGroundCmd(pivot)
//        }
//    }

    fun homeCmd(miyuki: Miyuki): ParallelGroup {
        return ParallelGroup(
//            LiftCmds.LiftHomeCmd(miyuki.lift),
//            ArmCmds.ArmHomeCmd(miyuki.arm),
//            PivotCmds.PivotHomeCmd(miyuki.pivot)
        )
    }

    fun triple(miyuki: Miyuki): ParallelGroup {
        return ParallelGroup(
//            liftCmd(miyuki.lift),
//            armCmd(miyuki.arm),
//            pivotCmd(miyuki.pivot)
        )
    }
}