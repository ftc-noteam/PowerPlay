package asiankoala.testing.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import asiankoala.testing.Robot
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.util.Alliance

@TeleOp
class KTeleOp(
    private val alliance: Alliance
) : KOpMode(photonEnabled = true) {
    private val startPose = Pose(-36.0, -60.0, 90.0.radians)
    private val robot by lazy { Robot(startPose) }

    override fun mInit() {
        // TODO: check if transfer function is acceptable
        robot.drive.setDefaultCommand(
            MecanumCmd(
                robot.drive,
                driver.leftStick,
                driver.rightStick,
                0.5,
                0.5,
                0.5,
                1.0,
                1.0,
                1.0,
                alliance,
                true,
                true,
                { robot.drive.pose.heading },
                60.0.radians
            )
        )
    }

    override fun mLoop() {
    }
}
