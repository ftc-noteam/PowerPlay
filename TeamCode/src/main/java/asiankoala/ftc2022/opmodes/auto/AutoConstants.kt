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

    @JvmField var depositToIntakeHeadingDeg = 250.0
    @JvmField var intakeX = -12.0
    @JvmField var intakeY = -58.5

    @JvmField var depositX = -10.0
    @JvmField var depositY = initDepositY
    @JvmField var depositHeadingDeg = 225.0
    @JvmField var depositPathHeadingDeg = 45.0

    @JvmField var liftDeltaHeightToPickupFuckingConeOffStack = 4.0
    @JvmField var liftHeight = 6.0

    @JvmField var intakeProjX = -12.0
    @JvmField var intakeProjY = -45.0

    @JvmField var readyProjX = -12.0
    @JvmField var readyProjY = -40.0

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

    val startPose = Pose(startPoseX, startPoseY, 180.0.radians)
    val liftHeights = List(5) { liftHeight - it }

    val initReadyProj = Vector(initReadyProjX, initReadyProjY)
    val depositProj = Vector(depositProjX, depositProjY)
    val intakeProj = Vector(intakeProjX, intakeProjY)
    val readyProj = Vector(readyProjX, readyProjY)

    private val initMiddlePose = Pose(middlePoseX, middlePoseY, middlePoseHeadingDeg.radians.angleWrap)
    private val initDepositPose = Pose(initDepositX, initDepositY, initDepositHeadingDeg.radians.angleWrap)
    private val initDepositToIntake = Pose(initDepositX, initDepositY, depositToIntakeHeadingDeg.radians.angleWrap)
    private val intakePose = Pose(intakeX, intakeY, 270.0.radians.angleWrap)
    private val intakeToDepositPose = Pose(intakeX, intakeY, 90.0.radians.angleWrap)
    private val depositPose = Pose(depositX, depositY, depositPathHeadingDeg.radians.angleWrap)
    private val depositToIntake = Pose(depositX, depositY, depositToIntakeHeadingDeg.radians.angleWrap)

    val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        initMiddlePose,
        initDepositPose
    )

    val initIntakePath = HermitePath(
        { 270.0.radians.angleWrap },
        initDepositToIntake,
        intakePose
    )

    val depositPath = HermitePath(
        { depositHeadingDeg.radians },
        intakeToDepositPose,
        depositPose
    )

    val intakePath = HermitePath(
        { 270.0.radians.angleWrap },
        depositPose,
        depositToIntake
    )

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
