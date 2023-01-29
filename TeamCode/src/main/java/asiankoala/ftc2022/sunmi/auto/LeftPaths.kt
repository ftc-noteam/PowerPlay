package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.subsystems.constants.FieldConstants
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HeadingController
import com.asiankoala.koawalib.path.HermitePath

val leftRightSideRobotStartPose = Pose(FieldConstants.startX, -FieldConstants.startY, 180.0.radians)
private val leftFirstDepositPathStartPose = leftRightSideRobotStartPose.headingFlip()
private val leftFirstDepositPathFirstMediumPose = Pose(-48.0, 34.75, 10.0.radians)
private val leftFirstDepositPathSecondMediumPose = Pose(-20.0, 37.0, 0.0)
private val leftDepositPose = FieldConstants.leftDeposit
private val leftIntakePathStartPose = leftDepositPose.headingFlip()
private val leftIntakePathMediumPose = Pose(-12.0, 48.0, (-270.0).radians)
private val leftIntakePathEndPose = Pose(-12.4, 60.9, (-270.0).radians)
private val leftDepositPathStartPose = leftIntakePathEndPose.headingFlip()
private val leftDepositPathMediumPose = leftIntakePathMediumPose.headingFlip().copy(x = -10.0, y = 36.0, heading = -65.0.radians)
private val leftAfterDepositPose = Pose(FieldConstants.afterDepositX, -FieldConstants.afterDepositY, -FieldConstants.afterDepositHeading.radians)

private val leftParkStartPose = leftAfterDepositPose.copy(x = -4.0, heading = 135.0.radians)
private val leftParkMediumEndPose = Pose(-12.0, 36.0, 135.0.radians)

private val leftParkLeftEndPose = Pose(-12.0, 58.0, 90.0.radians)
private val leftParkRightEndPose = Pose(-12.0, 12.0, (-90.0).radians)

private val initPath = HermitePath(
    HeadingController { v, t -> if(t < 0.55) v.angle else -FieldConstants.headingControllerDepositAngle.radians }.flip(),
    leftFirstDepositPathStartPose,
    leftFirstDepositPathFirstMediumPose,
    leftFirstDepositPathSecondMediumPose,
    leftDepositPose
)

private val intakePath = HermitePath(
    { _, _-> (-270.0).radians.angleWrap },
    leftIntakePathStartPose,
    leftIntakePathMediumPose,
    leftIntakePathEndPose
)

private val depositPath = HermitePath(
    HeadingController { v, t -> if(t < 0.2) v.angle else -FieldConstants.headingControllerDepositAngle.radians }.flip(),
    leftDepositPathStartPose,
    leftDepositPathMediumPose,
    leftAfterDepositPose
)

private val leftParkMediumPath = HermitePath(
    { _, _, -> 90.0.radians },
    leftParkStartPose,
    leftParkMediumEndPose
)

private val leftParkLeftPath = HermitePath(
    { _, _, -> 90.0.radians },
    leftParkMediumEndPose.copy(heading = 90.0.radians),
    leftParkLeftEndPose
)

private val leftParkRightPath = HermitePath(
    { _, _, -> 90.0.radians },
    leftParkMediumEndPose.copy(heading = (-90.0).radians),
    leftParkRightEndPose
)

private val leftPaths = listOf(initPath, intakePath, depositPath,
    leftParkMediumPath, leftParkLeftPath, leftParkRightPath)
val leftAutoPaths by lazy { AutoPaths(leftPaths) }
