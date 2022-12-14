package asiankoala.ftc2022

import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds
import asiankoala.ftc2022.subsystems.*
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import asiankoala.ftc2022.subsystems.constants.LiftConstants
import asiankoala.ftc2022.subsystems.vision.Vision
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive

class Miyuki(startPose: Pose) {
    val hardware = Hardware(startPose)
    val drive = KMecanumOdoDrive(
        hardware.fl,
        hardware.bl,
        hardware.fr,
        hardware.br,
        hardware.odometry,
        false
    )
    val lift = Lift(hardware.liftLead, hardware.liftBottom, hardware.liftLeft)
    val arm = Arm(hardware.arm)
    val pivot = Pivot(hardware.pivot)
    val claw = Claw(hardware.claw)
    val vision = Vision()

    var state = State.INTAKING
    var strategy = DepositState.MED

    val armCmd: Cmd
        get() = ArmCmds.ArmCmd(arm, when(strategy) {
                DepositState.GROUND -> ArmConstants.ground
                DepositState.LOW -> ArmConstants.low
                DepositState.MED -> ArmConstants.med
                DepositState.HIGH -> ArmConstants.high
            })

    val liftCmd: Cmd
        get() = LiftCmds.LiftCmd(lift, when(strategy) {
            DepositState.GROUND -> LiftConstants.ground
            DepositState.LOW -> LiftConstants.low
            DepositState.MED -> LiftConstants.med
            DepositState.HIGH -> LiftConstants.high
        })

    fun incStrat() {
        strategy = strategy.next
    }

    fun decStrat() {
        strategy = strategy.prev
    }

    init {
        Logger.logInfo("arm pos at init", arm.pos)
        arm.setPos(ArmConstants.pickup)
        lift.setPos(LiftConstants.home)
    }
}

enum class State {
    INTAKING,
    READYING,
    DEPOSITING,
    HOMING;

    val next get() = values()[(ordinal + 1) % values().size]
}

enum class DepositState {
    GROUND,
    LOW,
    MED,
    HIGH;

    val next get() = values()[(ordinal + 1) % values().size]
    val prev get() = values()[(ordinal - 1) % values().size]
}

