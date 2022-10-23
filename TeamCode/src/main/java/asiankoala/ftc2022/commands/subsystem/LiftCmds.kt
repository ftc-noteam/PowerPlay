package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Lift
import com.asiankoala.koawalib.command.commands.InstantCmd

object LiftCmds {
    open class LiftCmd(lift: Lift, pos: Double) : InstantCmd({ lift.setPos(pos) }, lift)

    class LiftHomeCmd(lift: Lift) : LiftCmd(lift, Lift.homePos)
    class LiftGroundCmd(lift: Lift) : LiftCmd(lift, Lift.groundPos)
    class LiftLowCmd(lift: Lift) : LiftCmd(lift, Lift.lowPos)
    class LiftMedCmd(lift: Lift) : LiftCmd(lift, Lift.medPos)
    class LiftHighCmd(lift: Lift) : LiftCmd(lift, Lift.highPos)
}