package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.Lift
import asiankoala.ftc2022.sunmi.subsystems.Pivot
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

class LiftTest : KOpMode(photonEnabled = true, maxParallelCommands = 8) {
    private lateinit var lift: Lift

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG

        lift = Lift(MotorFactory("bl")
            .brake
            .reverse
            .build()
        )
        // want to make sure that stuff is inited before moving lift
        val claw = Claw()
        val arm = Arm()
        val pivot = Pivot()

        driver.leftTrigger.onPress(InstantCmd({ lift.setTarget(6.0) }))
        driver.rightTrigger.onPress(InstantCmd({ lift.setTarget(1.0) }))
        driver.rightBumper.onPress(InstantCmd(lift::startAttemptingZero))
    }

    override fun mLoop() {
        Logger.addTelemetryData("attempting zero", lift.isAttemptingZero)
        Logger.addVar("p", lift.pos)
        Logger.addVar("v", lift.vel)
        Logger.addVar("target p", lift.setpoint.x)
        Logger.addVar("target v", lift.setpoint.v)
    }
}