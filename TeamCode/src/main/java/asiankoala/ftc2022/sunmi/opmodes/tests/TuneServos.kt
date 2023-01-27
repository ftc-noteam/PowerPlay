package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.Pivot
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class TuneServos : KOpMode(true, 8) {
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        val claw = Claw()
        val pivot = Pivot()
        val arm = Arm()
        driver.rightTrigger.onPress(InstantCmd({ arm.setTarget(ArmConstants.intake )}))
        driver.rightBumper.onPress(InstantCmd({ arm.setTarget(ArmConstants.home )}))
        driver.leftTrigger.onPress(InstantCmd({ claw.setTarget(ClawConstants.open )}))
        driver.leftBumper.onPress(InstantCmd({ claw.setTarget(ClawConstants.close )}))
        driver.dpadDown.onPress(ArmCmd(arm, ArmConstants.deposit))
        driver.dpadUp.onPress(PivotCmd(pivot, PivotConstants.home))
        driver.dpadLeft.onPress(PivotCmd(pivot, PivotConstants.deposit))
        driver.dpadRight.onPress(PivotCmd(pivot, PivotConstants.ground))
    }
}