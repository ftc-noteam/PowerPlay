package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object PivotConstants {
    @JvmField var home = 0.58
    @JvmField var deposit = 0.38

    var low = deposit
    var ground = home
    var med = deposit
    var high = deposit
}
