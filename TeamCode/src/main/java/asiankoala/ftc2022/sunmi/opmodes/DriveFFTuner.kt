package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.subsystems.constants.GVFConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.control.profile.MotionProfile
import com.asiankoala.koawalib.control.profile.MotionState
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.util.Clock
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.sign

@TeleOp
class DriveFFTuner : KOpMode() {
    class TuneCmd(private val drive: KMecanumOdoDrive) : Cmd() {
        private val profile = MotionProfile.generateTrapezoidal(
            MotionState(if(forward) 0.0 else 72.0),
            MotionState(if(forward) 72.0 else 0.0),
            MotionConstraints(DriveFFConfig.maxV, DriveFFConfig.maxA)
        )
        private val start by lazy { Clock.seconds }
        private var state = MotionState()
        private var dt = 0.0

        override fun initialize() {
            forward = !forward
        }

        override fun execute() {
            dt = Clock.seconds - start
            state = profile[dt]
            target = state.v
            vel = drive.vel.y
            error = target - vel
            val output = GVFConstants.kS * sign(state.v) +
                    GVFConstants.kV * state.v +
                    GVFConstants.kA * state.a
            drive.powers = Pose(0.0, output, 0.0)
        }

        override val isFinished: Boolean
            get() = dt > profile.duration

        init {
            addRequirements(drive)
        }
    }
    private val sunmi by lazy { Sunmi(Pose()) }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi.drive.defaultCommand = MecanumCmd(sunmi.drive, driver.leftStick, driver.rightStick)
        driver.rightTrigger.onPress(TuneCmd(sunmi.drive))
    }

    override fun mLoop() {
        Logger.addVar("vel", vel)
        Logger.addVar("target", target)
        Logger.addVar("error", error)
    }

    companion object {
        private var forward = false
        private var error = 0.0
        private var vel = 0.0
        private var target = 0.0
    }
}
