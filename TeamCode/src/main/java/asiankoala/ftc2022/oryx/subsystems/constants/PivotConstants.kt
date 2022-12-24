package asiankoala.ftc2022.oryx.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object PivotConstants {
    @JvmField var home = 0.8
    @JvmField var deposit = 1.0
    @JvmField var low = 0.65
    @JvmField var ground = home
    @JvmField var med = deposit
    @JvmField var high = deposit
}
