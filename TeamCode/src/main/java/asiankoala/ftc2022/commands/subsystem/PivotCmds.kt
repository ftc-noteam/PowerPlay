package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Pivot
import asiankoala.ftc2022.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

object PivotCmds {
    open class PivotCmd(private val pivot: Pivot, private val pos: Double) :
        InstantCmd({ pivot.setPos(pos) }, pivot)
    class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.home)
    class PivotDepositCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.deposit)
}