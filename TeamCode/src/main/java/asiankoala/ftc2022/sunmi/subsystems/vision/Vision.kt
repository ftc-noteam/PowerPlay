package asiankoala.ftc2022.sunmi.subsystems.vision

import asiankoala.ftc2022.sunmi.Zones
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.subsystem.vision.KWebcam
import org.openftc.easyopencv.OpenCvCameraRotation

class Vision: Subsystem() {
    private val pipeline = SleevePipeline()
    private val webcam = KWebcam(
        "Webcam",
        pipeline,
        800,
        448,
        OpenCvCameraRotation.UPRIGHT
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