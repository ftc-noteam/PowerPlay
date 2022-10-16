package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.MiyukiState
import asiankoala.ftc2022.Reversible
import com.asiankoala.koawalib.command.commands.InstantCmd
import asiankoala.ftc2022.subsystems.Pivot

class PivotCmds {
    open class PivotCmd(private val pivot: Pivot, private val pos: Reversible<Double>) : InstantCmd({ pivot.setPos(pos[MiyukiState.reversed]) }, pivot)

    class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.homePos)
    class PivotGroundCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.groundPos)
    class PivotLowCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.lowPos)
    class PivotMedCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.medPos)
    class PivotHighCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.highPos)
}