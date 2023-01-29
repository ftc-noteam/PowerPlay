package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.auto.*
import asiankoala.ftc2022.sunmi.commands.sequence.IdleSeq
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class SunmiRightAuto : KOpMode(true, 8) {
    private lateinit var sunmi: Sunmi

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
//        sunmi = Sunmi(rightSideRobotStartPose)
        sunmi = Sunmi(rightSideRobotStartPose)
        sunmi.vision.unregister()
        sunmi.strat = Strategy.HIGH

        val gen = CommandPathGen(sunmi)

        sunmi.drive.defaultCommand = DriveCmd(sunmi.drive, driver.leftStick, driver.rightStick)

        + SequentialGroup(
            ClawCloseCmd(sunmi.claw),
            WaitUntilCmd { opModeState == OpModeState.LOOP },
            gen.firstDepositWithCmd,
            JustDepositSeq(sunmi),

            gen.intakeWithCmd,
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            InstantCmd({ sunmi.stack-- }),

            gen.intakeWithCmd,
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            InstantCmd({ sunmi.stack-- }),

            gen.intakeWithCmd,
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            InstantCmd({ sunmi.stack-- }),


            gen.intakeWithCmd,
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            InstantCmd({ sunmi.stack-- }),


            gen.intakeWithCmd,
            WaitCmd(0.3),
            AutoIntakeSeq(sunmi),
            gen.depositWithCmd,
            JustDepositSeq(sunmi),

            InstantCmd({ sunmi.stack-- }),
        )
    }

    override fun mLoop() {
        Logger.drawRobot(sunmi.drive.pose)
    }
}