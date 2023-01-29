package asiankoala.ftc2022.sunmi.subsystems.constants

import com.acmerobotics.dashboard.config.Config

@Config
object OdoConstants {
    const val ticksPerUnit = 1892.3724
//    @JvmField var TRACK_WIDTH = (13.7 * 2.0) / 2.54 // prayge johnathan
//    @JvmField var PERP_TRACKER = -5.0903
//    @JvmField var TRACK_WIDTH = 11.1 // prayge johnathan
    @JvmField var PERP_TRACKER = -4.7848
    @JvmField var RIGHT_OFFSET = -5.35433
    val LEFT_OFFSET = -RIGHT_OFFSET + 6.0 / 25.4
}