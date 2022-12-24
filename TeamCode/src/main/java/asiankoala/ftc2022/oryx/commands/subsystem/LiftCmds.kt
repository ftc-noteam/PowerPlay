package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.subsystems.Lift
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

open class LiftCmd(private val lift: Lift, private val pos: Double) :
    InstantCmd({ lift.setTarget(pos) })

class LiftReadyCmd(lift: Lift) : LiftCmd(lift, LiftConstants.ready)
class LiftHomeCmd(lift: Lift) : LiftCmd(lift, LiftConstants.home)

class LiftStateCmd(lift: Lift, strat: Strategy) : LiftCmd(lift, when(strat) {
    Strategy.GROUND -> LiftConstants.ground
    Strategy.LOW -> LiftConstants.low
    Strategy.MED-> LiftConstants.med
    Strategy.HIGH -> LiftConstants.high
})
