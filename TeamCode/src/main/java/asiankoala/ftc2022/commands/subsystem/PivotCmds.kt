package asiankoala.ftc2022.commands.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import asiankoala.ftc2022.subsystems.Pivot

class PivotCmds {
    open class PivotCmd(pivot: Pivot, pos: Double) : InstantCmd({ pivot.setPosition(pos) }, pivot)

    class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.homePos)
    class PivotGroundCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.groundPos)
    class PivotLowCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.lowPos)
    class PivotMedCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.medPos)
    class PivotHighCmd(pivot: Pivot) : PivotCmd(pivot, Pivot.highPos)
}