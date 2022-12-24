package asiankoala.ftc2022.oryx

import asiankoala.ftc2022.oryx.subsystems.*
import asiankoala.ftc2022.oryx.subsystems.constants.OdoConstants
import asiankoala.ftc2022.oryx.subsystems.constants.ArmConstants
import asiankoala.ftc2022.oryx.subsystems.vision.Vision
import asiankoala.ftc2022.oryx.utils.Strategy
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.control.profile.v2.Constraints
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Oryx(pose: Pose) {
    val hw = Hardware()
    val odo = KThreeWheelOdometry(
        hw.leftEncoder,
        hw.rightEncoder,
        hw.auxEncoder,
        OdoConstants.TRACK_WIDTH,
        OdoConstants.PERP_TRACKER,
        pose
    )
    val drive = Drive(
        hw.fl,
        hw.bl,
        hw.br,
        hw.fr,
        odo,
        ::state
    )
    val lift = Lift(
        hw.lrt,
        hw.lrb,
        hw.llt,
        hw.llb
    )
    val claw = Claw(hw.claw)
    val pivot = Pivot(hw.pivot)
    val arm = Arm(
        hw.armL,
        hw.armR,
        hw.axonEnc,
        Constraints(ArmConstants.vel, ArmConstants.accel),
        ArmConstants.epsilon
    )
    val vision = Vision()
    var state = State.INTAKING
    var strategy = Strategy.HIGH
}