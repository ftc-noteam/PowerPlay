package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Claw
import com.asiankoala.koawalib.command.commands.InstantCmd

object ClawCmds {
    open class ClawCmd(claw: Claw, pos: Pair<Double, Double>) : InstantCmd({ claw.setPos(pos) }, claw)

    class ClawGripCmd(claw: Claw) : ClawCmd(claw, Claw.gripPos)
    class ClawHomeCmd(claw: Claw) : ClawCmd(claw, Claw.homePos)
    class ClawDepositCmd(claw: Claw) : ClawCmd(claw, Claw.depositPos)
}