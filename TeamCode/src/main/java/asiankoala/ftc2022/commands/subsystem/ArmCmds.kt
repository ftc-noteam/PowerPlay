package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.ArmConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

object ArmCmds {
    open class ArmCmd(private val arm: Arm, private val pos: Double) :
            InstantCmd({ arm.setPos(pos) })

    class ArmHomeCmd(arm: Arm) : ArmCmd(arm, ArmConstants.home)
    class ArmDepositCmd(arm: Arm) : ArmCmd(arm, ArmConstants.deposit)
}