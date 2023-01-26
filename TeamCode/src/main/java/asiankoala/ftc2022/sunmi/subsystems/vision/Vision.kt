package asiankoala.ftc2022.sunmi.subsystems.vision

import asiankoala.ftc2022.sunmi.Zones
import com.asiankoala.koawalib.subsystem.KSubsystem
import com.asiankoala.koawalib.subsystem.vision.KWebcam
import org.openftc.easyopencv.OpenCvCameraRotation

class Vision: KSubsystem() {
    private val pipeline = SleevePipeline()
    private val webcam = KWebcam(
        "webcam",
        pipeline,
        800,
        448,
        OpenCvCameraRotation.UPSIDE_DOWN
    )
    var zone = Zones.WTF
        private set

    override fun periodic() {
        zone = pipeline.zone
    }

    fun start() {
        webcam.startStreaming()
    }

    fun stop() {
        webcam.stopStreaming()
        pipeline.release()
    }
}