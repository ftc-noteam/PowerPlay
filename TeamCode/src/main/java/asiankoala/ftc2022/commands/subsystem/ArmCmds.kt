package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.subsystems.Arm
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.util.Reversible

object ArmCmds {
    open class ArmCmd(private val arm: Arm, private val pos: Reversible<Double>) : InstantCmd({ arm.setPos(pos[MiyukiState.reversed]) }, arm)

    class ArmHomeCmd(arm: Arm) : ArmCmd(arm, Arm.homePos)
    class ArmGroundCmd(arm: Arm) : ArmCmd(arm, Arm.groundPos)
    class ArmLowCmd(arm: Arm) : ArmCmd(arm, Arm.lowPos)
    class ArmMedCmd(arm: Arm) : ArmCmd(arm, Arm.medPos)
    class ArmHighCmd(arm: Arm) : ArmCmd(arm, Arm.highPos)
}