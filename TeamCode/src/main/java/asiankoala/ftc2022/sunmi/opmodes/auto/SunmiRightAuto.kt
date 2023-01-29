package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.Zones
import asiankoala.ftc2022.sunmi.auto.*
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@Autonomous
open class SunmiRightAuto : KOpMode(true, 8) {
    private lateinit var sunmi: Sunmi
    protected open val autoPaths by lazy { rightAutoPaths }
    protected open val startPose by lazy { rightSideRobotStartPose }

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        sunmi = Sunmi(rightSideRobotStartPose)
        sunmi.vision.start()
        sunmi.strat = Strategy.HIGH
        val gen = CommandPathGen(sunmi, rightAutoPaths)

        + SequentialGroup(
            ClawCloseCmd(sunmi.claw),
            WaitUntilCmd { opModeState == OpModeState.LOOP },
            gen.firstDepositWithCmd,
            JustDepositSeq(sunmi),

            gen.intakeWithCmd(LiftConstants.fiveHeight),
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            gen.intakeWithCmd(LiftConstants.fourHeight),
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            gen.intakeWithCmd(LiftConstants.threeHeight),
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            ChooseCmd(
                gen.leftPark,
                ChooseCmd(
                    gen.midPark,
                    gen.rightPark
                ) { sunmi.vision.zone == Zones.MIDDLE }
            ) { sunmi.vision.zone == Zones.LEFT },
        )
    }

    override fun mStart() {
        sunmi.vision.stop()
        sunmi.vision.unregister()
    }
}