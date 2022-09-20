package asiankoala.ftc2022

import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot

class Strategy(
    private val lift: Lift,
    private val arm: Arm,
    private val pivot: Pivot
) {
    var strategy = Strats.HIGH

    fun getLiftCmd(): LiftCmds.LiftCmd {
        return when(strategy) {
            Strats.HIGH -> LiftCmds.LiftHighCmd(lift)
            Strats.MEDIUM -> LiftCmds.LiftMedCmd(lift)
            Strats.LOW -> LiftCmds.LiftLowCmd(lift)
            Strats.GROUND -> LiftCmds.LiftGroundCmd(lift)
            else -> LiftCmds.LiftHomeCmd(lift)
        }
    }

    fun getArmCmd(): ArmCmds.ArmCmd {
        return when(strategy) {
            Strats.HIGH -> ArmCmds.ArmHighCmd(arm)
            Strats.MEDIUM -> ArmCmds.ArmMedCmd(arm)
            Strats.LOW -> ArmCmds.ArmLowCmd(arm)
            Strats.GROUND -> ArmCmds.ArmGroundCmd(arm)
            else -> ArmCmds.ArmHomeCmd(arm)
        }
    }

    fun getPivotCmd(): PivotCmds.PivotCmd {
        return when(strategy) {
            Strats.HIGH -> PivotCmds.PivotHighCmd(pivot)
            Strats.MEDIUM -> PivotCmds.PivotMedCmd(pivot)
            Strats.LOW -> PivotCmds.PivotLowCmd(pivot)
            Strats.GROUND -> PivotCmds.PivotGroundCmd(pivot)
            else -> PivotCmds.PivotHomeCmd(pivot)
        }
    }
}