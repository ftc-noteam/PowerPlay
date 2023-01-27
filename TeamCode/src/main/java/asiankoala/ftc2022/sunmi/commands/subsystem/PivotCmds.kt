package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Pivot
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class PivotCmd(private val pivot: Pivot, private val pos: Double) :
    InstantCmd({ pivot.setTarget(pos) })

class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.home)
