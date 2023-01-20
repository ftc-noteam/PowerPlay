package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class ServoTestLol : KOpMode() {
    private val arm1 by lazy { KServo("arm1").startAt(ArmTestConstants.up) }
    private val arm2 by lazy { KServo("arm2").startAt(ArmTestConstants.up).reverse() }
    private val p by lazy { KServo("pivot").startAt(0.5) }
    private val c by lazy { KServo("claw").startAt(ClawTestConstants.closed) }

    private fun setArmPosition(pos: Double) {
        arm1.position = pos
        arm2.position = pos
    }
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        driver.leftTrigger.onPress(InstantCmd({ p.position = 0.5 }))
        driver.rightTrigger.onPress(InstantCmd({ p.position = 0.6 }))
        driver.leftBumper.onPress(InstantCmd({ c.position = ClawTestConstants.open }))
        driver.rightBumper.onPress(InstantCmd({ c.position = ClawTestConstants.closed }))
        driver.x.onPress(InstantCmd({ setArmPosition(ArmTestConstants.down) }))
        driver.b.onPress(InstantCmd({ setArmPosition(ArmTestConstants.downOther) }))
        driver.y.onPress(InstantCmd({ setArmPosition(ArmTestConstants.up) }))
    }
}