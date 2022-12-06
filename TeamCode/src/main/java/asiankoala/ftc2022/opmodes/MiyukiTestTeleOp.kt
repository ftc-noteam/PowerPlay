package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class MiyukiTestTeleOp : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        miyuki.hardware.odometry.unregister()
        miyuki.vision.unregister()
        miyuki.lift.unregister()
        miyuki.arm.unregister()
        miyuki.drive.unregister()
//        scheduleDrive()
//        scheduleStrat()
//        scheduleCycling()
//        driver.a.onPress(ArmCmds.ArmCmd(miyuki.arm, 0.0))
//        driver.b.onPress(ArmCmds.ArmCmd(miyuki.arm, 90.0))
        driver.leftTrigger.onPress(InstantCmd({ miyuki.hardware.fl.power = 0.1 }))
        driver.leftBumper.onPress(InstantCmd({ miyuki.hardware.bl.power = 0.1 }))
        driver.rightBumper.onPress(InstantCmd({ miyuki.hardware.br.power = 0.1 }))
        driver.rightTrigger.onPress(InstantCmd({ miyuki.hardware.fr.power = 0.1 }))

    }

    override fun mLoop() {
//        Logger.addTelemetryData("arm", miyuki.hardware.arm.pos)
//        Logger.addVar("arm x", miyuki.hardware.arm.pos)
//        Logger.addVar("arm v", miyuki.hardware.arm.vel)
//        Logger.addVar("setpoint", miyuki.hardware.arm.setpoint.v)
//        Logger.addTelemetryData("lift", miyuki.hardware.liftLead.pos)
//        Logger.addTelemetryData("is trigger just pressed", driver.rightTrigger.isJustPressed)
    }
}
