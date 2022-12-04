package asiankoala.ftc2022.subsystems

import asiankoala.ftc2022.Zones
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.subsystem.vision.KWebcam

class Vision: Subsystem() {
    private val pipeline = SleevePipeline()
    private val webcam = KWebcam("Webcam", pipeline)
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