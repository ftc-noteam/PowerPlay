package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class ClawCmd(claw: Claw, pos: Double) :
    InstantCmd({ claw.setPos(pos) })
class ClawCloseCmd(claw: Claw) : ClawCmd(claw, ClawConstants.close)
class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.open)
