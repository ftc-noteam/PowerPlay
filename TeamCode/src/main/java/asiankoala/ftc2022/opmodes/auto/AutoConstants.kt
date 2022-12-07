package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController

@Config
object AutoConstants {
    @JvmField var kN = 0.6
    @JvmField var kOmega = 30.0
    @JvmField var kF = 4.0
    @JvmField var kS = 1.0
    @JvmField var epsilon = 2.0
    @JvmField var thetaEpsilon = 5.0

    @JvmField var startPoseX = -66.0
    @JvmField var startPoseY = -36.0

    @JvmField var middlePoseX = -24.0
    @JvmField var middlePoseY = -36.0
    @JvmField var middlePoseHeadingDeg = 0.0

    @JvmField var depositX = -8.0
    @JvmField var depositY = -32.0
    @JvmField var initDepositHeadingDeg = 25.0

    @JvmField var initReadyProjX = -17.0
    @JvmField var initReadyProjY = -36.0

    @JvmField var depositProjX = -8.0
    @JvmField var depositProjY = -33.0

    @JvmField var depositToIntakeHeadingDeg = 250.0
    @JvmField var intakeX = -12.0
    @JvmField var intakeY = -65.0

    @JvmField var depositHeadingDeg = 60.0

    @JvmField var deltaLift = 4.0
    @JvmField var liftHeight = 6.0

    @JvmField var intakeProjX = -12.0
    @JvmField var intakeProjY = -45.0

    @JvmField var readyProjX = -12.0
    @JvmField var readyProjY = -40.0

    fun getGVFCmd(miyuki: Miyuki, path: Path, vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    val startPose = Pose(startPoseX, startPoseY)
    private val depositVec = Vector(depositX, depositY)
    private val intakeVec = Vector(intakeX, intakeY)
    val initReadyProj = Vector(initReadyProjX, initReadyProjY)
    val depositProj = Vector(depositProjX, depositProjY)
    val liftHeights = List(5) { liftHeight - it }
    val intakeProj = Vector(intakeProjX, intakeProjY)
    val readyProj = Vector(readyProjX, readyProjY)

    val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        Pose(middlePoseX, middlePoseY, middlePoseHeadingDeg.radians),
        Pose(depositVec, initDepositHeadingDeg.radians)
    )

    val intakePath = HermitePath(
        { 270.0.radians },
        Pose(depositVec, depositToIntakeHeadingDeg.radians),
        Pose(intakeVec, 270.0.radians)
    )

    val depositPath = HermitePath(
        { 45.0.radians },
        Pose(intakeVec, 90.0.radians),
        Pose(depositVec, depositHeadingDeg.radians)
    )
}










