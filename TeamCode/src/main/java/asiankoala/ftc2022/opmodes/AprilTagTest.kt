package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.subsystems.vision.Vision
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class AprilTagTest : KOpMode() {
    private lateinit var vision: Vision

    override fun mInit() {
        vision = Vision()
        vision.start()
    }

    override fun mInitLoop() {
        Logger.addTelemetryData("zone", vision.zone)
    }

    override fun mStart() {
        vision.stop()
        vision.unregister()
    }

    override fun mLoop() {
        Logger.addTelemetryData("zone", vision.zone)
    }
}