package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object PivotConstants {
    @JvmField var home = 0.46
    @JvmField var deposit = home - 0.2
    @JvmField var ground = home
}
