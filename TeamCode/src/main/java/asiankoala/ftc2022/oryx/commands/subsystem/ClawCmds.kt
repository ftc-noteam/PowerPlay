package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.subsystems.Claw
import asiankoala.ftc2022.oryx.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class ClawCmd(claw: Claw, pos: Double) :
    InstantCmd({ claw.setPos(pos) })
class ClawGripCmd(claw: Claw) : ClawCmd(claw, ClawConstants.close)
class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.open)
