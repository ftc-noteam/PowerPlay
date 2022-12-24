package asiankoala.ftc2022.oryx.utils

import com.asiankoala.koawalib.hardware.KDevice
import com.asiankoala.koawalib.math.angleWrap
import com.qualcomm.robotcore.hardware.AnalogInput
import kotlin.math.PI

class AxonServoEncoder(name: String): KDevice<AnalogInput>(name) {
    private val analogRange: Double = 3.3
    private var zeroPos = 0.0
    private var isReversed = false
    var angle = 0.0
        private set

    val reverse: AxonServoEncoder
        get() {
            isReversed = true
            return this
        }

    fun zero(x: Double): AxonServoEncoder {
        zeroPos = x
        return this
    }

    fun update() {
        var raw = device.voltage / analogRange
        if(isReversed) raw = 1.0 - raw
        angle = (raw * 2 * PI - zeroPos).angleWrap
    }
}
