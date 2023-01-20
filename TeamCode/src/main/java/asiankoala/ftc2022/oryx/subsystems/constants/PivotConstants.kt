package asiankoala.ftc2022.oryx.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object PivotConstants {
    @JvmField var home = 0.5
    @JvmField var deposit = 0.6
    @JvmField var low = deposit
    @JvmField var ground = home
    @JvmField var med = deposit
    @JvmField var high = deposit
}
