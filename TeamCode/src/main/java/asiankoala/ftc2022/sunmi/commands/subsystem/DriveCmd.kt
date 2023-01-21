package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.gamepad.KButton
import com.asiankoala.koawalib.gamepad.KStick
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import kotlin.math.abs
import kotlin.math.pow

class DriveCmd(
    private val drive: KMecanumDrive,
    private val leftStick: KStick,
    private val rightStick: KStick,
    private val rb: KButton
) : Cmd() {
    private val scalars: List<Double>
        get() = if(rb.isToggled) DriveConstants.slowScalars else DriveConstants.speedScalars

    private val fx: (Double) -> Double = { 2.6 * it * (0.98 * abs(it).pow(3) - 0.98 / 5.0 + 1) }

    override fun execute() {
        val raws = listOf(
            leftStick.xAxis,
            -leftStick.yAxis,
            -rightStick.xAxis
        )

        drive.powers = raws
            .mapIndexed { i, x -> fx.invoke(x) * scalars[i] }
            .let { Pose(it[0], it[1], it[2]) }
    }

    override val isFinished = false

    init {
        addRequirements(drive)
    }
}
