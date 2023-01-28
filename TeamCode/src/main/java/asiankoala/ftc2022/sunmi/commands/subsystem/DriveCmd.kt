package asiankoala.ftc2022.sunmi.commands.subsystem

import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.gamepad.KStick
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign

class DriveCmd(
    private val drive: KMecanumDrive,
    private val leftStick: KStick,
    private val rightStick: KStick
) : Cmd() {
    private val fastScalars = listOf(1.0, 1.0, 0.85)

    private fun joystickFunction(s: Double, k: Double, x: Double): Double {
        return max(0.0, s * x * (k * x.pow(3) - k + 1)) * x.sign
    }

    override fun execute() {
        val raws = listOf(
            leftStick.xAxis,
            -leftStick.yAxis,
            -rightStick.xAxis
        )

        drive.powers = raws
            .mapIndexed { i, d -> joystickFunction(fastScalars[i], 1.0, d) }
            .let { Pose(it[0], it[1], it[2]) }
    }

    init {
        addRequirements(drive)
    }
}