package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd


open class ArmCmd(private val arm: Arm, private val pos: Double) :
    InstantCmd({ arm.setTarget(pos) })

class ArmHomeCmd(arm: Arm) : ArmCmd(arm, ArmConstants.transit)

class ArmStateCmd(arm: Arm, strat: Strategy) : ArmCmd(arm, when(strat) {
    Strategy.GROUND -> ArmConstants.ground
    Strategy.LOW -> ArmConstants.low
    Strategy.MED-> ArmConstants.med
    Strategy.HIGH -> ArmConstants.high
})
