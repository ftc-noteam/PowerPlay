package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.subsystems.constants.FieldConstants
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HeadingController
import com.asiankoala.koawalib.path.HermitePath

fun Pose.headingFlip() = copy(heading = (this.heading + 180.0.radians).angleWrap)
// robot heading is 180, path heading is 0
val rightSideRobotStartPose = Pose(FieldConstants.startX, FieldConstants.startY, 180.0.radians)
val firstDepositPathStartPose = rightSideRobotStartPose.headingFlip()
val firstDepositPathFirstMediumPose = Pose(-48.0, -34.0, (-10.0).radians)
val firstDepositPathSecondMediumPose = Pose(-24.0, -36.0, 0.0)
val depositPose = Pose(FieldConstants.depositX, FieldConstants.depositY, FieldConstants.depositHeading.radians)
val intakePathStartPose = depositPose.headingFlip()
val intakePathMediumPose = Pose(-12.0, -48.0, 270.0.radians)
val intakePathEndPose = Pose(-12.0, -62.0, 270.0.radians)
val depositPathStartPose = intakePathEndPose.headingFlip()
val depositPathMediumPose = intakePathMediumPose.headingFlip()

val initPath = HermitePath(
    HeadingController { v, t -> if(t < 0.55) v.angle else FieldConstants.headingControllerDepositAngle.radians }.flip(),
    firstDepositPathStartPose,
    firstDepositPathFirstMediumPose,
    firstDepositPathSecondMediumPose,
    depositPose
)

val intakePath = HermitePath(
    DEFAULT_HEADING_CONTROLLER,
    intakePathStartPose,
    intakePathMediumPose,
    intakePathEndPose
)

val depositPath = HermitePath(
    DEFAULT_HEADING_CONTROLLER.flip(),
    depositPathStartPose,
    depositPathMediumPose,
    depositPose
)