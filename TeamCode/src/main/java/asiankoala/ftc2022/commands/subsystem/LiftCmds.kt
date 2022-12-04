package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.LiftConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

object LiftCmds {
    open class LiftCmd(lift: Lift, pos: Double) : InstantCmd({ lift.setPos(pos) }, lift)
    class LiftHomeCmd(lift: Lift) : LiftCmd(lift, LiftConstants.home)
    class LiftReadyCmd(lift: Lift) : LiftCmd(lift, LiftConstants.med)
}