package asiankoala.ftc2022.testing

import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.Pivot
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class ArmTest : KOpMode() {
    override fun mInit() {
        val arm = Arm()
        val pivot = Pivot()
        val claw = Claw()
        driver.rightTrigger.onPress(InstantCmd({ arm.setTarget(ArmConstants.init) }))
        driver.leftTrigger.onPress(InstantCmd({ arm.setTarget(ArmConstants.intake) }))
        driver.leftBumper.onPress(InstantCmd({ pivot.setPos(PivotConstants.home) }))
        driver.rightBumper.onPress(InstantCmd({ pivot.setPos(PivotConstants.deposit) }))
        driver.y.onPress(InstantCmd({ arm.setTarget(ArmConstants.gidle) }))
        driver.a.onPress(InstantCmd({ arm.setTarget(ArmConstants.deposit) }))
        driver.dpadLeft.onPress(InstantCmd({ claw.setPos(ClawConstants.close) }))
        driver.dpadDown.onPress(InstantCmd({ claw.setPos(ClawConstants.open) }))
    }
}