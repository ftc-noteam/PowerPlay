package asiankoala.ftc2022.opmodes.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.opmodes.auto.AutoConstants.choose
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
@Config
object AutoConstants {
    @JvmField var kN = 0.5
    @JvmField var kOmega = 30.0
    @JvmField var kF = 4.0
    @JvmField var kS = 0.4
    @JvmField var epsilon = 2.0
    @JvmField var thetaEpsilon = 5.0

    @JvmField var middlePoseHeadingDeg = 0.0
    @JvmField var initDepositHeadingDeg = 25.0
    @JvmField var depositToIntakeHeadingDeg = 250.0
    @JvmField var depositHeadingDeg = 60.0

    @JvmField var startVec = Vector(-66.0, -36.0)
    @JvmField var middleVec = Vector(-24.0, -36.0)
    @JvmField var depositVec = Vector(-8.0, -32.0)
    @JvmField var initReadyProjVec = Vector(-17.0, -36.0)
    @JvmField var depositProjVec = Vector(-8.0, -33.0)
    @JvmField var intakeVec = Vector(-12.0, -65.0)
    @JvmField var intakeProjVec = Vector(-12.0, -45.0)
    @JvmField var readyProjVec = Vector(-12.0, -40.0)

    @JvmField var liftDeltaHeightToPickupFuckingConeOffStack = 4.0
    @JvmField var liftHeight = 6.0

    fun getGVFCmd(miyuki: Miyuki, path: Path, vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    val startPose = Pose(startVec, 180.0.radians)
    val liftHeights = List(5) { liftHeight - it }

    val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose,
        Pose(middleVec, middlePoseHeadingDeg.radians),
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

    fun <T> Boolean.choose(a: T, b: T) = if (this) a else b
    fun <T> T.cond(cond: Boolean, f: (T) -> T) = cond.choose(f.invoke(this), this)

    fun Vector.choose(alliance: Alliance, far: Boolean) =
        this
            .cond(alliance == Alliance.RED) { Vector(-x, y) }
            .cond(far) { Vector(x, -y) }

    fun HermitePath.choose(alliance: Alliance, close: Boolean) =
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