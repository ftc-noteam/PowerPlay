package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.OdoConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class OdometryTest : KOpMode(true, 8) {
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG

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

        val odo = KThreeWheelOdometry(
            EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fl),
            EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(br),
            EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fr),
            OdoConstants.TRACK_WIDTH,
            OdoConstants.PERP_TRACKER,
            Pose()
        )
        val drive = KMecanumOdoDrive(fl, bl, br, fr, odo, true)

        drive.defaultCommand = MecanumCmd(drive, driver.leftStick, driver.rightStick, scalars = Pose(1.0, 1.0, 0.4))
    }
}