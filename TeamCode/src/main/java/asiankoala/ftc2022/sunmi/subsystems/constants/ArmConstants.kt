package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ArmConstants {
    @JvmField var init = 0.5
    @JvmField var intake = 0.15
    @JvmField var gidle = 0.25
    @JvmField var deposit = 0.7

    @JvmField var ground = 0.0
    @JvmField var low = 0.0
    @JvmField var med = 0.0
    @JvmField var high = 0.0
    @JvmField var transit = init
}