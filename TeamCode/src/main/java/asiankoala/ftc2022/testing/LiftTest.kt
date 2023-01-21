package asiankoala.ftc2022.testing

import asiankoala.ftc2022.sunmi.subsystems.Lift
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class LiftTest : KOpMode() {
    private lateinit var l: Lift

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        l = Lift(
            MotorFactory("bl")
                .brake
                .reverse
                .build()
        )
        driver.rightTrigger.onPress(InstantCmd({ l.setTarget(16.0) }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("lift", l.pos)
    }
}