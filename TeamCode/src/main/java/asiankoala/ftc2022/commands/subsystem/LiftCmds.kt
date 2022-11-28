package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.LiftConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

object LiftCmds {
    open class LiftCmd(lift: Lift, pos: Double) : InstantCmd({ lift.setPos(pos) }, lift)

    class LiftHomeCmd(lift: Lift) : LiftCmd(lift, LiftConstants.homePos)
    class LiftGroundCmd(lift: Lift) : LiftCmd(lift, LiftConstants.groundPos)
    class LiftLowCmd(lift: Lift) : LiftCmd(lift, LiftConstants.lowPos)
    class LiftMedCmd(lift: Lift) : LiftCmd(lift, LiftConstants.medPos)
    class LiftHighCmd(lift: Lift) : LiftCmd(lift, LiftConstants.highPos)

    class LiftOpenLoopCmd(lift: Lift, power: Double) : InstantCmd({ lift.openLoop(power) }, lift)
}