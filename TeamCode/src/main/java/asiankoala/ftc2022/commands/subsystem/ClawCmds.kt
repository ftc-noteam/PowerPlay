package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.ClawConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

object ClawCmds {
    open class ClawCmd(claw: Claw, pos: Double) :
        InstantCmd({ claw.setPos(pos) }, claw)

    class ClawGripCmd(claw: Claw) : ClawCmd(claw, ClawConstants.grip)
    class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.open)
    class ClawDepositCmd(claw: Claw) : ClawCmd(claw, ClawConstants.deposit)
}