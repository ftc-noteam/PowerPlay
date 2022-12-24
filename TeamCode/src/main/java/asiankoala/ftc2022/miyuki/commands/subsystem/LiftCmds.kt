package asiankoala.ftc2022.miyuki.commands.subsystem

import asiankoala.ftc2022.miyuki.subsystems.Lift
import asiankoala.ftc2022.miyuki.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class LiftCmd(lift: Lift, pos: Double) : InstantCmd({ lift.setPos(pos) })
class LiftHomeCmd(lift: Lift) : LiftCmd(lift, LiftConstants.home)
class LiftReadyCmd(lift: Lift) : LiftCmd(lift, LiftConstants.med)
class LiftHighCmd(lift: Lift) : LiftCmd(lift, LiftConstants.high)
