package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.subsystems.constants.DriveConstants
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.gamepad.KButton
import com.asiankoala.koawalib.gamepad.KStick
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import java.lang.Math.abs
import kotlin.math.pow

class DriveCmd(
    private val drive: KMecanumDrive,
    private val leftStick: KStick,
    private val rightStick: KStick,
    private val rb: KButton
) : Cmd() {
    private val scalars: List<Double>
        get() = if(rb.isToggled) DriveConstants.slowScalars else DriveConstants.speedScalars

    private fun joystickFunction(s: Double, k: Double, x: Double): Double {
        return 2.6 * x * (k * abs(x).pow(3) - k / 5.0 + 1)
    }

    override fun execute() {
        val raws = listOf(
            leftStick.xAxis,
            -leftStick.yAxis,
            -rightStick.xAxis
        )

        drive.powers = raws
            .mapIndexed { i, d -> joystickFunction(scalars[i], 0.98, d) }
            .let { Pose(it[0], it[1], it[2]) }
    }

    override val isFinished = false

    init {
        addRequirements(drive)
    }
}
