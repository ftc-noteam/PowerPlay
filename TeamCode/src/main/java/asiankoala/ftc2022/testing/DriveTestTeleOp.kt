package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class DriveTestTeleOp : KOpMode() {
    override fun mInit() {
        Logger.config = LoggerConfig.COMP_CONFIG

        val fl = MotorFactory("fl")
            .brake
            .reverse
            .build()

        val bl = MotorFactory("bl")
            .brake
            .reverse
            .build()

        val br = MotorFactory("br")
            .brake
            .forward
            .build()

        val fr = MotorFactory("fr")
            .brake
            .forward
            .build()

        val drive = KMecanumDrive(fl, bl, br, fr)

        drive.defaultCommand = MecanumCmd(
            drive,
            driver.leftStick,
            driver.rightStick,
        )
    }
}