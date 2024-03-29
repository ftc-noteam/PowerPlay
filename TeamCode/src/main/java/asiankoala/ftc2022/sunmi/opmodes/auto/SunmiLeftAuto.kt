package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.Zones
import asiankoala.ftc2022.sunmi.auto.*
import asiankoala.ftc2022.sunmi.commands.subsystem.ClawCloseCmd
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


@Autonomous
class SunmiLeftAuto : KOpMode(true, 8) {
    private lateinit var sunmi: Sunmi

    override fun mInit() {
        Logger.config = LoggerConfig.SIMPLE_CONFIG
        sunmi = Sunmi(leftRightSideRobotStartPose)
        sunmi.vision.start()
        sunmi.strat = Strategy.HIGH
        val gen = CommandPathGen(sunmi, leftAutoPaths)

        + SequentialGroup(
            ClawCloseCmd(sunmi.claw),
            WaitUntilCmd { opModeState == OpModeState.LOOP },
            gen.firstDepositWithCmd,
            JustDepositSeq(sunmi),

            gen.medPark,

            ChooseCmd(
                gen.leftPark,
                ChooseCmd(
                    InstantCmd({}),
                    gen.rightPark
                ) { sunmi.vision.zone == Zones.MIDDLE }
            ) { sunmi.vision.zone == Zones.LEFT },
        )
    }

    override fun mInitLoop() {
        Logger.addTelemetryData("zone", sunmi.vision.zone)
    }

    override fun mStart() {
        sunmi.vision.stop()
        sunmi.vision.unregister()
    }
}
