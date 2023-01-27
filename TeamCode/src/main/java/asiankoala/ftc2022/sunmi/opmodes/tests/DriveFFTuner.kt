package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.subsystems.constants.DriveFFConfig
import asiankoala.ftc2022.sunmi.subsystems.constants.GVFConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.control.profile.MotionProfile
import com.asiankoala.koawalib.control.profile.MotionState
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.util.Clock
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.sign

@TeleOp
class DriveFFTuner : KOpMode() {
    private val sunmi by lazy { Sunmi(Pose()) }
    private var forward = true
    private var error = 0.0
    private var vel = 0.0
    private var target = 0.0
    private var start = Clock.seconds
    private var state = MotionState()
    private var dt = 100000.0 // just init to big value so profile is generated in start
    private lateinit var profile: MotionProfile

    private fun genProfile() {
        forward = !forward
        start = Clock.seconds
        profile = MotionProfile.generateTrapezoidal(
            MotionState(if (forward) 0.0 else 72.0),
            MotionState(if (forward) 72.0 else 0.0),
            MotionConstraints(DriveFFConfig.maxV, DriveFFConfig.maxA)
        )
    }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
    }

    override fun mLoop() {
        if(dt > profile.duration) genProfile()

        dt = Clock.seconds - start
        state = profile[dt]
        target = state.v
        vel = sunmi.drive.vel.y
        error = target - vel
        val output = GVFConstants.kS * sign(state.v) +
                GVFConstants.kV * state.v +
                GVFConstants.kA * state.a

        sunmi.drive.powers = Pose(0.0, output, 0.0)

        Logger.addVar("vel", vel)
        Logger.addVar("target", target)
        Logger.addVar("error", error)
    }
}
