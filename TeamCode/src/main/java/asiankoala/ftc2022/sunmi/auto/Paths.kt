package asiankoala.ftc2022.sunmi.auto

import asiankoala.ftc2022.sunmi.subsystems.constants.FieldConstants
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.HeadingController
import com.asiankoala.koawalib.path.HermitePath

fun Pose.headingFlip() = copy(heading = (this.heading + 180.0.radians).angleWrap)
val rightSideRobotStartPose = Pose(FieldConstants.startX, FieldConstants.startY, 180.0.radians)
val firstDepositPathStartPose = rightSideRobotStartPose.headingFlip()
val firstDepositPathFirstMediumPose = Pose(-48.0, -34.75, (-10.0).radians)
val firstDepositPathSecondMediumPose = Pose(-24.0, -36.0, 0.0)
val depositPose = Pose(FieldConstants.depositX, FieldConstants.depositY, FieldConstants.depositHeading.radians)
val intakePathStartPose = depositPose.headingFlip()
val intakePathMediumPose = Pose(-12.0, -48.0, 270.0.radians)
val intakePathEndPose = Pose(-12.4, -60.9, 270.0.radians)
val depositPathStartPose = intakePathEndPose.headingFlip()
val depositPathMediumPose = intakePathMediumPose.headingFlip().copy(x = -10.0, y = -36.0, heading = 65.0.radians)
val afterDepositPose = Pose(FieldConstants.afterDepositX, FieldConstants.afterDepositY, FieldConstants.afterDepositHeading.radians)
val parkMiddleStartPose = afterDepositPose.copy(x = -4.0, heading = (-135.0).radians)
val parkMiddleEndPose = Pose(-12.0, -36.0, (-135.0).radians)
val parkLeftStartPose = parkMiddleStartPose
val parkLeftEndPose = Pose(-12.0, -12.0, 90.0.radians)
val parkRightStartPose = parkMiddleStartPose
val parkRightEndPose = Pose(-12.0, -58.0, (-90.0).radians)

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

private val leftParkPath = HermitePath(
    { _, _ -> (-90.0).radians },
    parkLeftStartPose,
    parkLeftEndPose
)

private val middleParkPath = HermitePath(
    { _, _ -> (-90.0).radians },
    parkMiddleStartPose,
    parkMiddleEndPose
)

private val rightParkPath = HermitePath(
    { _, _ -> (-90.0).radians },
    parkRightStartPose,
    parkRightEndPose
)

data class AutoPaths(
    val paths: List<HermitePath>
) {
    val initPath: HermitePath = paths[0]
    val intakePath: HermitePath = paths[1]
    val depositPath: HermitePath = paths[2]
    val leftParkPath: HermitePath = paths[3]
    val middleParkPath: HermitePath = paths[4]
    val rightParkPath: HermitePath = paths[5]
}

fun flipPose(it: Pose) = Pose(it.x, -it.y, -it.heading.angleWrap )
fun flipPath(path: HermitePath) = path.map(::flipPose)
private val rightPaths = listOf(initPath, intakePath, depositPath, leftParkPath, middleParkPath, rightParkPath)
val rightAutoPaths by lazy { AutoPaths(rightPaths) }
val leftAutoPaths by lazy { AutoPaths(listOf(
    flipPath(initPath),
    flipPath(intakePath),
    flipPath(depositPath),
    flipPath(rightParkPath),
    flipPath(middleParkPath),
    flipPath(leftParkPath)
)) }
