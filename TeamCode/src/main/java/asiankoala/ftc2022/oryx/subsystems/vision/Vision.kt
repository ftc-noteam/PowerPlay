package asiankoala.ftc2022.oryx.subsystems.vision

import asiankoala.ftc2022.oryx.Zones
import com.asiankoala.koawalib.subsystem.KSubsystem
import com.asiankoala.koawalib.subsystem.vision.KWebcam
import org.openftc.easyopencv.OpenCvCameraRotation

class Vision: KSubsystem() {
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