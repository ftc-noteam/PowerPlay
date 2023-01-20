package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.KMotor

open class FourMotorTest(
    private val first: KMotor,
    private val second: KMotor,
    private val third: KMotor,
    private val fourth: KMotor
) : KOpMode() {
    override fun mInit() {
        driver.leftBumper.onPress(InstantCmd({ first.power = 1.0 }))
        driver.leftTrigger.onPress(InstantCmd({ first.power = 0.0 }))

        driver.rightBumper.onPress(InstantCmd({ second.power = 1.0 }))
        driver.rightTrigger.onPress(InstantCmd({ second.power = 0.0 }))

        driver.dpadLeft.onPress(InstantCmd({ third.power = 1.0 }))
        driver.dpadRight.onPress(InstantCmd({ third.power = 0.0 }))

        driver.dpadUp.onPress(InstantCmd({ fourth.power = 1.0 }))
        driver.dpadDown.onPress(InstantCmd({ fourth.power = 0.0 }))
    }
}