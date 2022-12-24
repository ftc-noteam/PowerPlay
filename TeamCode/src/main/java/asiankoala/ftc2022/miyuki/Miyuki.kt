package asiankoala.ftc2022.miyuki

import asiankoala.ftc2022.miyuki.commands.subsystem.ArmCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.LiftCmd
import asiankoala.ftc2022.miyuki.subsystems.Arm
import asiankoala.ftc2022.miyuki.subsystems.Claw
import asiankoala.ftc2022.miyuki.subsystems.Lift
import asiankoala.ftc2022.miyuki.subsystems.Pivot
import asiankoala.ftc2022.miyuki.subsystems.constants.ArmConstants
import asiankoala.ftc2022.miyuki.subsystems.constants.LiftConstants
import asiankoala.ftc2022.oryx.subsystems.vision.Vision
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Miyuki(startPose: Pose) {
    val hardware = Hardware(startPose)
    val odometry = KThreeWheelOdometry(
        hardware.leftEncoder,
        hardware.rightEncoder,
        hardware.auxEncoder,
        Hardware.TRACK_WIDTH,
        Hardware.PERP_TRACKER,
        startPose
    )
    val drive = KMecanumOdoDrive(
        hardware.fl,
        hardware.bl,
        hardware.br,
        hardware.fr,
        odometry,
        true
    )
    val lift = Lift(hardware.liftLead, hardware.liftBottom, hardware.liftLeft)
    val arm = Arm(hardware.arm)
    val pivot = Pivot(hardware.pivot)
    val claw = Claw(hardware.claw)
    val vision = Vision()

    var state = State.INTAKING
    var strategy = DepositState.MED

    val armCmd: Cmd
        get() = ArmCmd(arm, when(strategy) {
                DepositState.GROUND -> ArmConstants.ground
                DepositState.LOW -> ArmConstants.low
                DepositState.MED -> ArmConstants.med
                DepositState.HIGH -> ArmConstants.high
            })

    val liftCmd: Cmd
        get() = LiftCmd(lift, when(strategy) {
            DepositState.GROUND -> LiftConstants.ground
            DepositState.LOW -> LiftConstants.low
            DepositState.MED -> LiftConstants.med
            DepositState.HIGH -> LiftConstants.high
        })

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
}

enum class DepositState {
    GROUND,
    LOW,
    MED,
    HIGH;
}

enum class Zones {
    LEFT,
    MIDDLE,
    RIGHT,
    WTF
}
