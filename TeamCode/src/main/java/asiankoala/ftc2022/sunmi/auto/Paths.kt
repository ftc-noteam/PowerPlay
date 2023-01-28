package asiankoala.ftc2022.sunmi.auto

import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath

// robot heading is 180, path heading is 0
val rightSideRobotStartPose = Pose(-66.0, -36.0, 180.0)
val firstDepositPathStartPose = rightSideRobotStartPose.copy(heading = 0.0)
val firstDepositPathMediumPose = Pose(-24.0, -36.0, 0.0)
val depositPose = Pose(-6.0, -30.0, 45.0.radians)
val intakePathStartPose = depositPose.copy(heading = 250.0.radians)
val intakePathEndPose = Pose(-12.0, -65.0, 270.0.radians)
val depositPathStartPose = intakePathEndPose.copy(heading = 90.0.radians)

val initPath = HermitePath(
    DEFAULT_HEADING_CONTROLLER,
    firstDepositPathStartPose,
    firstDepositPathMediumPose,
    depositPose
)

val intakePath = HermitePath(
    DEFAULT_HEADING_CONTROLLER.flip(),
    intakePathStartPose,
    intakePathEndPose
)

val depositPath = HermitePath(
    DEFAULT_HEADING_CONTROLLER,
    depositPathStartPose,
    depositPose
)