package asiankoala.ftc2022.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.util.Alliance
import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.path.HermiteSplineInterpolator
import com.asiankoala.koawalib.path.HermiteType
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState

@TeleOp
class KTeleOp(
    private val alliance: Alliance,
    private val startPose: Pose
) : KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(startPose) }

    override fun mInit() {
        scheduleDrive()
        scheduleStrategy()
        scheduleCycling()
    }

    private fun scheduleDrive() {
        // TODO: check if transfer function is acceptable
        miyuki.drive.setDefaultCommand(
            MecanumCmd(
                miyuki.drive,
                driver.leftStick,
                driver.rightStick,
                0.5,
                0.5,
                0.5,
                1.0,
                1.0,
                1.0,
                alliance,
                isTranslationFieldCentric = true,
                isHeadingFieldCentric = true,
                { miyuki.drive.pose.heading },
                60.0.radians
            )
        )

        val testPath = Path(
            HermiteSplineInterpolator(
                HermiteType.CUBIC,
                { it.angle },
                Pose(),
                Pose(24.0, 24.0)
            )
        )

        + SequentialGroup(
            WaitUntilCmd { opmodeState == OpModeState.START },
            GVFCmd(
                miyuki.drive,
                SimpleGVFController(
                    testPath,
                    0.6,
                    1.0 / 22.5,
                    4.0,
                    0.95,
                    2.0
                )
            )
        )
    }

    private fun scheduleStrategy() {
        TODO()
    }

    private fun scheduleCycling() {
        TODO()
    }

    override fun mLoop() {
    }
}

