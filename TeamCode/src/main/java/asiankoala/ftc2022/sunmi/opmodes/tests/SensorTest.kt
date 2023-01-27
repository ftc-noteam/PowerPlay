package asiankoala.ftc2022.sunmi.opmodes.tests

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class SensorTest : KOpMode(true, 8) {
    private lateinit var sensor: KDistanceSensor

    override fun mInit() {
        sensor = KDistanceSensor("distance", 200.0)
    }

    override fun mLoop() {
        sensor.periodic()
        Logger.addTelemetryData("last read", sensor.lastRead)
    }
}