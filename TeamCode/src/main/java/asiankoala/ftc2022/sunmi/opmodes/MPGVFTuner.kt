package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.subsystems.constants.GVFConstants
import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.gvf.BetterMotionProfileGVFController

@Config
class MPGVFTuner : KOpMode() {
    private val sunmi by lazy { Sunmi(Pose()) }
    private val forwardPath = HermitePath(
        DEFAULT_HEADING_CONTROLLER,
        Pose(),
        Pose(48.0)
    )
    private val backwardPath = forwardPath.flip()
    private lateinit var controller: BetterMotionProfileGVFController

    private fun genGVFController(path: HermitePath) = BetterMotionProfileGVFController(
        path,
        sunmi.drive,
        GVFConstants.kN,
        GVFConstants.kTheta,
        GVFConstants.kOmega,
        GVFConstants.epsilon,
        GVFConstants.thetaEpsilon,
        GVFConstants.constraints,
        GVFConstants.kS,
        GVFConstants.kV,
        GVFConstants.kA
    )

    override fun mInit() {
        driver.leftTrigger.onPress(InstantCmd({
            controller = genGVFController(forwardPath)
            + GVFCmd(sunmi.drive, controller)
        }))

        driver.rightTrigger.onPress(InstantCmd({
            controller = genGVFController(backwardPath)
            + GVFCmd(sunmi.drive, controller)
        }))
    }

    override fun mLoop() {
        val vel = sunmi.drive.vel.x
        val target = controller.state.v
        val error = target - vel
        Logger.addVar("vel", vel)
        Logger.addVar("target vel", target)
        Logger.addVar("error", error)
    }
}