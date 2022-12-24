package asiankoala.ftc2022.miyuki.commands.subsystem

import asiankoala.ftc2022.miyuki.subsystems.Arm
import asiankoala.ftc2022.miyuki.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class ArmCmd(private val arm: Arm, private val pos: Double) :
    InstantCmd({ arm.setPos(pos) })
class ArmPickupCmd(arm: Arm) : ArmCmd(arm, ArmConstants.pickup)
class ArmHighCmd(arm: Arm) : ArmCmd(arm, ArmConstants.high)
