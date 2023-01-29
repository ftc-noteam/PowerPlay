package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians

@Config
object FieldConstants {
    @JvmField var startX = -61.3
    @JvmField var startY = -33.0
    @JvmField var depositX = -6.9
    @JvmField var depositY = -28.7
    @JvmField var depositHeading = 65.0
    @JvmField var headingControllerDepositAngle = 45.0
    @JvmField var afterDepositX = -4.2
    @JvmField var afterDepositY = -28.7
    @JvmField var afterDepositHeading = 48.0

    val leftDeposit = Pose(-4.0, -28.0, 150.0.radians)
}