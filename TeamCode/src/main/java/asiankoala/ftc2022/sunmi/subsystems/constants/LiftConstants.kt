package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object LiftConstants {
    @JvmField var kP = 0.35
    @JvmField var kD = 0.005
    @JvmField var kG = 0.1
    @JvmField var vel = 80.0
    @JvmField var accel = 80.0
    @JvmField var home = 0.0
    @JvmField var ground = 0.0
    @JvmField var low = 6.5
    @JvmField var med = 16.5
    @JvmField var high = 26.5
    @JvmField var coneDelta = 4.0
    @JvmField var stackSafeRaiseDelta = 6.0
    @JvmField var stackSafeRaiseHeight = 0.0
    @JvmField var homeAfterIntaking = -0.5
    val stackPositions = List(5) { coneDelta * it }
}