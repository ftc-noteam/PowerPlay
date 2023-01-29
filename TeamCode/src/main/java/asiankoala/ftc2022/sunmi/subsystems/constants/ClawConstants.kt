package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ClawConstants {
    @JvmField var open = 0.38
    @JvmField var close = 0.49
    @JvmField var intakeThreshold = 45.0
    @JvmField var semiOpenForAuto = 0.45
}
