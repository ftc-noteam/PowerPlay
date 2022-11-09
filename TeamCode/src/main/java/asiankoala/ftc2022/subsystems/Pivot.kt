package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.Subsystem
import com.asiankoala.koawalib.util.Reversible

class Pivot(private val servo: KServo) : Subsystem() {
    @Config
    companion object {
        @JvmField var homePos = Reversible(0.0, 0.0)
        @JvmField var groundPos = Reversible(0.0, 0.0)
        @JvmField var lowPos = Reversible(0.0, 0.0)
        @JvmField var medPos = Reversible(0.0, 0.0)
        @JvmField var highPos = Reversible(0.0, 0.0)
    }

    fun setPos(pos: Double) {
        servo.position = pos
    }
}