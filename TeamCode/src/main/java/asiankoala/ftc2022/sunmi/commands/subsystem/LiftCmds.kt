package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Lift
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import asiankoala.ftc2022.sunmi.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

open class LiftCmd(private val lift: Lift, private val pos: Double) :
    InstantCmd({ lift.setTarget(pos) })

class LiftReadyCmd(lift: Lift) : LiftCmd(lift, LiftConstants.home)
class LiftHomeCmd(lift: Lift) : LiftCmd(lift, LiftConstants.home)

class LiftStateCmd(lift: Lift, strat: Strategy) : LiftCmd(lift, when(strat) {
    Strategy.GROUND -> LiftConstants.ground
    Strategy.LOW -> LiftConstants.low
    Strategy.MED-> LiftConstants.med
    Strategy.HIGH -> LiftConstants.high
})
