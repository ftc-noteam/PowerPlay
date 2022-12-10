package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
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
fixed
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

    @JvmField var liftDeltaHeightToPickupFuckingConeOffStack = 3.0
    @JvmField var liftHeight = 4.0

    fun getGVFCmd(
        miyuki: Miyuki,
        path: Path,
        kN: Double = AutoConstants.kN,
        kOmega: Double = AutoConstants.kOmega,
        kF: Double = AutoConstants.kF,
        kS: Double = AutoConstants.kS,
        epsilon: Double = AutoConstants.epsilon,
        thetaEpsilon: Double = AutoConstants.thetaEpsilon,
        vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    fun getGVFCmd(
        miyuki: Miyuki,
        path: Path,
        vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    val startPose = Pose(startPoseX, startPoseY, 180.0.radians)
    val liftHeights = List(5) { liftHeight - it }


    private val initMiddlePose = Pose(-24.0, -36.0, 0.0)
    private val initDepositPose = Pose(-8.0, -28.0, 40.0.radians)
    private val initDepositToIntake = initDepositPose.copy(heading = 250.0.radians.angleWrap)
    private val intakePose = Pose(-12.0, -58.5, 270.0.radians.angleWrap)
    private val intakeToDepositPose = intakePose.copy(heading = 90.0.radians)
    private val depositPose = Pose(-12.0, initDepositPose.y, 45.0.radians)
    private val depositToIntake = depositPose.copy(heading = 250.0.radians.angleWrap)


    val initReadyProj = Vector(-36.0, -36.0)
    val depositProj = Vector(initDepositPose.x - 0.5, initDepositPose.y - 0.5)
    val intakeProj = Vector(-12.0, -45.0)
    val readyProj = Vector(-12.0, -40.0)

    val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        initMiddlePose,
        initDepositPose
    )

    val bruhPath = HermitePath(
        { 270.0.radians.angleWrap },
        initDepositPose.copy(heading = 225.0.radians),
        Pose(-12.0, -36.0, 225.0.radians)
    )

    val initIntakePath = HermitePath(
        { 270.0.radians.angleWrap },
        initDepositToIntake,
        intakePose
    )

    val depositPath = HermitePath(
        { 225.0.radians.angleWrap },
        intakeToDepositPose,
        depositPose
    )

    val intakePath = HermitePath(
        { 270.0.radians.angleWrap },
        depositPose,
        depositToIntake
    )

    val parkLeftPath = HermitePath(
        { 270.0.radians.angleWrap },
        depositPose.copy(heading = 90.0.radians),
        Pose(-12.0, -12.0, 90.0.radians)
    )

    val parkRightPath = HermitePath(
        { 270.0.radians.angleWrap },
        depositPose.copy(heading = 270.0.radians.angleWrap),
        Pose(-12.0, -54.0, 270.0.radians.angleWrap)
    )

    fun getParkVector(alliance: Alliance, far: Boolean, zone: Zones): Vector {
        return when(zone) {
            Zones.LEFT -> Vector(-12.0, -12.0)
            Zones.MIDDLE -> Vector(-12.0, -36.0)
            Zones.RIGHT -> Vector(-12.0, 54.0)
            else -> Vector()
        }.choose(alliance, far)
    }

    fun <T> Boolean.choose(a: T, b: T) = if (this) a else b
    fun <T> T.cond(cond: Boolean, f: (T) -> T) = cond.choose(f.invoke(this), this)

    fun Vector.choose(alliance: Alliance, far: Boolean) =
        this
            .cond(alliance == Alliance.RED) { Vector(-x, y) }
            .cond(far) { Vector(x, -y) }

    fun HermitePath.choose(alliance: Alliance, far: Boolean) =
        this
            .cond(alliance == Alliance.RED) {
                this.map(this.headingController.flip()) {
                    Pose(
                        -it.x,
                        it.y,
                        (180.0.radians - it.heading).angleWrap
                    )
                }
            }
            .cond(far) {
                this.map(this.headingController) {
                    Pose(
                        it.x,
                        -it.y,
                        -it.heading
                    )
                }
            }

    fun Pose.choose(alliance: Alliance, far: Boolean) =
        this
            .cond(alliance == Alliance.RED) { Pose(-x, y, (heading + 180.0).angleWrap) }
            .cond(far) { Pose(x, -y, heading)}
}
