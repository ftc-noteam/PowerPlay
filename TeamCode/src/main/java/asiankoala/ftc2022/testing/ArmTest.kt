package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class ArmTest : KOpMode(photonEnabled = true, maxParallelCommands = 8) {
    private val arm1 by lazy {
        KServo("arm1").startAt(ArmTestConstants.up)
    }
    private val arm2 by lazy {
        KServo("arm2").startAt(ArmTestConstants.up)
            .reverse()
    }

    private fun setArmPosition(pos: Double) {
        arm1.position = pos
        arm2.position = pos
    }

    override fun mInit() {
        driver.x.onPress(InstantCmd({ setArmPosition(ArmTestConstants.down) }))
        driver.b.onPress(InstantCmd({ setArmPosition(ArmTestConstants.downOther) }))
        driver.y.onPress(InstantCmd({ setArmPosition(ArmTestConstants.up) }))
    }
}