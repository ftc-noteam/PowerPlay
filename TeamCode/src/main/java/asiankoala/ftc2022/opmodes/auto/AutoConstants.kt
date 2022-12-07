package asiankoala.ftc2022.opmodes.auto

import com.acmerobotics.dashboard.config.Config

@Config
object AutoConstants {
    @JvmField var kN = 0.6
    @JvmField var kOmega = 30.0
    @JvmField var kF = 4.0
    @JvmField var kS = 1.0
    @JvmField var epsilon = 2.0
    @JvmField var thetaEpsilon = 5.0

    @JvmField var startPoseX = -66.0
    @JvmField var startPoseY = -36.0

    @JvmField var depositX = -8.0
    @JvmField var depositY = -32.0
    @JvmField var initDepositHeadingDeg = 25.0

    @JvmField var initReadyX = -17.0
    @JvmField var initReadyY = -36.0

    @JvmField var depositProjX = -8.0
    @JvmField var depositProjY = -33.0

    @JvmField var intakeX = -12.0
    @JvmField var intakeY = -65.0

    @JvmField var depositHeadingDeg = 60.0

    @JvmField var deltaLift = 4.0
    @JvmField var liftHeight = 6.0

    @JvmField var intakeReadyX = -12.0
    @JvmField var intakeReadyY = -45.0

    @JvmField var readyX = -12.0
    @JvmField var readyY = -40.0
}