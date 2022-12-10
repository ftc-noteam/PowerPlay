package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.*
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.Alliance

// assuming blue side close
/*
notes: when going for intake,
the bot is somewhat too far to the left (so too much x), although this doesnt show on pose
also, heading is really important, 0.5 degrees off is too much
needs to go a bit further

when we go back from normal deposit to intake, it hits the ground (ADD ANOTHER CONTROL POSE)
 */
@Config
object AutoConstants {
    @JvmField var kN = 0.5
    @JvmField var kOmega = 30.0
    @JvmField var kF = 4.0
    @JvmField var kS = 0.4
    @JvmField var epsilon = 2.0
    @JvmField var thetaEpsilon = 5.0

    @JvmField var startPoseX = -63.0
    @JvmField var startPoseY = -36.0
    @JvmField var middlePoseX = -24.0
    @JvmField var middlePoseY = -36.0
    @JvmField var middlePoseHeadingDeg = 0.0

    @JvmField var initDepositX = -12.0
    @JvmField var initDepositY = -32.0
    @JvmField var initDepositHeadingDeg = 40.0

    @JvmField var initReadyProjX = -17.0
    @JvmField var initReadyProjY = -36.0

    @JvmField var depositProjX = -8.0
    @JvmField var depositProjY = -33.0

    @JvmField var depositToIntakeHeadingDeg = 270.0
    @JvmField var intakeX = -12.0
    @JvmField var intakeY = -58.5

    @JvmField var depositX = -10.0
    @JvmField var depositY = initDepositY
    @JvmField var depositHeadingDeg = 180.0 + 45.0
    @JvmField var depositPathHeadingDeg = 45.0

    @JvmField var liftDeltaHeightToPickupFuckingConeOffStack = 4.0
    @JvmField var liftHeight = 6.0

    @JvmField var intakeProjX = -12.0
    @JvmField var intakeProjY = -45.0

    @JvmField var readyProjX = -12.0
    @JvmField var readyProjY = -40.0

    fun getGVFCmd(miyuki: Miyuki, path: Path, vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    val startPose = Pose(startPoseX, startPoseY, 180.0.radians)
    val initDepositVec = Vector(initDepositX, initDepositY)
    val depositVec = Vector(depositX, depositY)
    val intakeVec = Vector(intakeX, intakeY)
    val middleVec = Vector(middlePoseX, middlePoseY)
    val initReadyProj = Vector(initReadyProjX, initReadyProjY)
    val depositProj = Vector(depositProjX, depositProjY)
    val liftHeights = List(5) { liftHeight - it }
    val intakeProj = Vector(intakeProjX, intakeProjY)
    val readyProj = Vector(readyProjX, readyProjY)

    val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        Pose(middleVec, middlePoseHeadingDeg.radians),
        Pose(initDepositVec, initDepositHeadingDeg.radians)
    )

    val initIntakePath = HermitePath(
        { 270.0.radians.angleWrap },
        Pose(initDepositVec, depositToIntakeHeadingDeg.radians),
        Pose(intakeVec, 270.0.radians)
    )

    val depositPath = HermitePath(
        { depositHeadingDeg.radians },
        Pose(intakeVec, 90.0.radians),
        Pose(depositVec, depositPathHeadingDeg.radians)
    )

    val intakePath = HermitePath(
        { 270.0.radians.angleWrap },
        Pose(depositVec, depositToIntakeHeadingDeg.radians),
        Pose(intakeVec, 270.0.radians)
    )

    private fun <T> Boolean.choose(a: T, b: T) = if (this) a else b
    private fun <T> T.cond(cond: Boolean, f: (T) -> T) = cond.choose(f.invoke(this), this)

    private fun Vector.choose(alliance: Alliance, close: Boolean) =
        this
            .cond(alliance == Alliance.RED) { Vector(-x, y) }
            .cond(close) { Vector(x, -y) }

    private fun HermitePath.choose(alliance: Alliance, close: Boolean) =
        this
            .cond(alliance == Alliance.RED) {
                this.map(FLIPPED_HEADING_CONTROLLER) {
                    Pose(
                        -it.x,
                        it.y,
                        (180.0.radians - it.heading).angleWrap
                    )
                }
            }
            .cond(close) {
                this.map(DEFAULT_HEADING_CONTROLLER) {
                    Pose(
                        it.x,
                        -it.y,
                        -it.heading
                    )
                }
            }
}










