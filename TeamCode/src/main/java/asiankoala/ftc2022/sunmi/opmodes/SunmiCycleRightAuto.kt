package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.auto.CommandPathGen
import asiankoala.ftc2022.sunmi.auto.rightSideRobotStartPose
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCloseCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCmd
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class SunmiCycleRightAuto : KOpMode(true, 8) {
    private lateinit var sunmi: Sunmi

    override fun mInit() {
        Logger.config = LoggerConfig.COMP_CONFIG
        sunmi = Sunmi(rightSideRobotStartPose)
        sunmi.vision.unregister()
        sunmi.strat = Strategy.HIGH

        val gen = CommandPathGen(sunmi)

        + SequentialGroup(
            ClawCmd(sunmi.claw, ClawConstants.semiOpenForAuto),
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
            ClawCloseCmd(sunmi.claw),
            WaitUntilCmd { opModeState == OpModeState.LOOP },
            gen.firstDepositNoCmd,
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
            gen.intakeNoCmd,
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
            gen.depositNoCmd,
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
            gen.intakeNoCmd,
            WaitUntilCmd(driver.rightTrigger::isJustPressed),
        )
    }
}