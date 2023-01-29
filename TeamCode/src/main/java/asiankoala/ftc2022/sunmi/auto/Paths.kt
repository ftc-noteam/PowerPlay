package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.subsystems.constants.FieldConstants
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HeadingController
import com.asiankoala.koawalib.path.HermitePath

fun Pose.headingFlip() = copy(heading = (this.heading + 180.0.radians).angleWrap)
val rightSideRobotStartPose = Pose(FieldConstants.startX, FieldConstants.startY, 180.0.radians)
private val firstDepositPathStartPose = rightSideRobotStartPose.headingFlip()
private val firstDepositPathFirstMediumPose = Pose(-48.0, -34.75, (-10.0).radians)
private val firstDepositPathSecondMediumPose = Pose(-24.0, -36.0, 0.0)
private val depositPose = Pose(FieldConstants.depositX, FieldConstants.depositY, FieldConstants.depositHeading.radians)
private val intakePathStartPose = depositPose.headingFlip()
private val intakePathMediumPose = Pose(-12.0, -48.0, 270.0.radians)
private val intakePathEndPose = Pose(-12.4, -60.9, 270.0.radians)
private val depositPathStartPose = intakePathEndPose.headingFlip()
private val depositPathMediumPose = intakePathMediumPose.headingFlip().copy(x = -11.0, y = -34.0, heading = 65.0.radians)
private val afterDepositPose = Pose(FieldConstants.afterDepositX, FieldConstants.afterDepositY, FieldConstants.afterDepositHeading.radians)

private val rightParkStartPose = afterDepositPose.copy(x = -4.0, heading = (-135.0).radians)
private val rightParkMediumEndPose = Pose(-12.0, -42.0, (-135.0).radians)

private val rightParkLeftEndPose = Pose(-12.0, -13.5, 90.0.radians)
private val rightParkRightEndPose = Pose(-12.0, -58.0, (-90.0).radians)

private val initPath = HermitePath(
    HeadingController { v, t -> if(t < 0.55) v.angle else FieldConstants.headingControllerDepositAngle.radians }.flip(),
    firstDepositPathStartPose,
    firstDepositPathFirstMediumPose,
    firstDepositPathSecondMediumPose,
    depositPose
)

private val intakePath = HermitePath(
    { _, _-> 270.0.radians.angleWrap },
    intakePathStartPose,
    intakePathMediumPose,
    intakePathEndPose
)

private val depositPath = HermitePath(
    HeadingController { v, t -> if(t < 0.2) v.angle else FieldConstants.headingControllerDepositAngle.radians }.flip(),
    depositPathStartPose,
    depositPathMediumPose,
    afterDepositPose
)

private val rightParkMediumPath = HermitePath(
    { _, _, -> 90.0.radians },
    rightParkStartPose,
    rightParkMediumEndPose
)

private val rightParkLeftPath = HermitePath(
    { _, _, -> 90.0.radians },
    rightParkMediumEndPose.copy(heading = 90.0.radians),
    rightParkLeftEndPose
)

private val rightParkRightPath = HermitePath(
    { _, _, -> 90.0.radians },
    rightParkMediumEndPose.copy(heading = (-90.0).radians),
    rightParkRightEndPose
)

data class AutoPaths(
    val paths: List<HermitePath>
) {
    val initPath: HermitePath = paths[0]
    val intakePath: HermitePath = paths[1]
    val depositPath: HermitePath = paths[2]
    val parkMediumPath: HermitePath = paths[3]
    val parkLeftPath: HermitePath = paths[4]
    val parkRightPath: HermitePath = paths[5]
}

private val rightPaths = listOf(initPath, intakePath, depositPath, rightParkMediumPath, rightParkLeftPath, rightParkRightPath)
val rightAutoPaths by lazy { AutoPaths(rightPaths) }
