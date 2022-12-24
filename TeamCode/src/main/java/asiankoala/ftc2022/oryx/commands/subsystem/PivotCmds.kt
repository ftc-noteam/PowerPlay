package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.subsystems.Pivot
import asiankoala.ftc2022.oryx.subsystems.constants.PivotConstants
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

open class PivotCmd(private val pivot: Pivot, private val pos: Double) :
    InstantCmd({ pivot.setPos(pos) })

class PivotHomeCmd(pivot: Pivot) : PivotCmd(pivot, PivotConstants.home)

class PivotStateCmd(pivot: Pivot, strat: Strategy) : PivotCmd(pivot, when(strat) {
    Strategy.GROUND -> PivotConstants.ground
    Strategy.LOW -> PivotConstants.low
    Strategy.MED-> PivotConstants.med
    Strategy.HIGH -> PivotConstants.high
})
