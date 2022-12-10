package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class ArmCmd(private val arm: Arm, private val pos: Double) :
    InstantCmd({ arm.setPos(pos) }, arm)
class ArmPickupCmd(arm: Arm) : ArmCmd(arm, ArmConstants.pickup)
class ArmHighCmd(arm: Arm) : ArmCmd(arm, ArmConstants.high)
