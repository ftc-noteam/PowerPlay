package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.subsystems.Pivot
import asiankoala.ftc2022.subsystems.PivotConstants
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.util.Reversible

class PivotCmds {
    open class PivotCmd(private val pivot: Pivot, private val pos: Reversible<Double>) :
        InstantCmd({ pivot.setPos(pos[MiyukiState.reversed]) }, pivot)

    class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.homePos)
    class PivotGroundCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.groundPos)
    class PivotLowCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.lowPos)
    class PivotMedCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.medPos)
    class PivotHighCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.highPos)
}