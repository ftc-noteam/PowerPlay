package asiankoala.testing.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import asiankoala.testing.Robot
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.path.Path

@TeleOp
class KTeleOp : KOpMode(photonEnabled = true) {
    private val startPose = Pose(-36.0, -60.0, 90.0.radians)
    private val robot by lazy { Robot(startPose) }

    override fun mInit() {
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
                1.0
            )
        )

        driver.x.onPress(InstantCmd({ driver.rumbleBlips(3) }))
        driver.y.onPress(InstantCmd({ driver.rumble(2500) }))

        val path = Path(
            startPose,
            Pose(-24.0, -36.0, 45.0.radians),
            Pose(-14.0, 0.0, 90.0.radians)
        )

        driver.a.onPress(
            GVFCmd(
                robot.drive,
                path,
                0.65,
                1.0 / 25.0,
                4.0,
                1.0,
                2.0
            )
        )
    }

    override fun mLoop() {
//        Logger.addTelemetryData("arm angle", robot.arm.motor.pos)
//        Logger.addTelemetryData("is at target", robot.arm.motor.isAtTarget())
//        Logger.addVar("target vel", robot.arm.motor.setpoint.v)
//        Logger.addVar("curr vel", robot.arm.motor.currState.v)
    }
}
