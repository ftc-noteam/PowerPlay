package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

class DriveTest : KOpMode(true, 8) {
    override fun mInit() {
        val fl = MotorFactory("fl")
            .brake
            .reverse
            .build()
        val bl = MotorFactory("bl")
            .brake
            .reverse
            .withStaticFeedforward(DriveConstants.blKStatic)
            .build()
        val br = MotorFactory("br")
            .brake
            .forward
            .withStaticFeedforward(DriveConstants.brKStatic)
            .build()
        val fr = MotorFactory("fr")
            .brake
            .forward
            .build()

        val drive = KMecanumDrive(fl, bl, br, fr)
        drive.defaultCommand = MecanumCmd(drive, driver.leftStick, driver.rightStick)
    }
}