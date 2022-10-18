package asiankoala.ftc2022.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.util.Alliance
import asiankoala.ftc2022.Miyuki

@TeleOp
class KTeleOp(
    private val alliance: Alliance,
    private val startPose: Pose
) : KOpMode(photonEnabled = true) {
    private val miyuki by lazy { Miyuki(startPose) }

    override fun mInit() {
        bindDrive()
        bindStrategy()
        bindCycling()
    }

    private fun bindDrive() {
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
    }

    private fun bindStrategy() {
        TODO()
    }

    private fun bindCycling() {
        TODO()
    }

    override fun mLoop() {
    }
}

