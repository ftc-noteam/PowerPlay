package asiankoala.ftc2022.miyuki.commands.subsystem

import asiankoala.ftc2022.miyuki.subsystems.Claw
import asiankoala.ftc2022.miyuki.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class ClawCmd(claw: Claw, pos: Double) :
    InstantCmd({ claw.setPos(pos) })
class ClawGripCmd(claw: Claw) : ClawCmd(claw, ClawConstants.grip)
class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.open)
class ClawDepositCmd(claw: Claw) : ClawCmd(claw, ClawConstants.deposit)
