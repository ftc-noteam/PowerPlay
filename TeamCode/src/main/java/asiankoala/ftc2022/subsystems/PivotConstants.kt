package asiankoala.ftc2022.subsystems

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.util.Reversible

@Config
object PivotConstants {
    @JvmField var pivotHome = 0.77 // reverse
    @JvmField var pivotDeposit = 0.95

    var homePos = Reversible(pivotHome, 0.4)
    var groundPos = Reversible(0.0, 0.0)
    var lowPos = Reversible(0.0, 0.0)
    var medPos = Reversible(0.0, 0.0)
    var highPos = Reversible(0.0, 0.32)
}