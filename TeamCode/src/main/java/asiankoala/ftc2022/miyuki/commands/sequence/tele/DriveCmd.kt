package asiankoala.ftc2022.miyuki.commands.sequence.tele

import asiankoala.ftc2022.oryx.subsystems.constants.DriveConstants
import asiankoala.ftc2022.oryx.utils.State
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.gamepad.KButton
import com.asiankoala.koawalib.gamepad.KStick
import com.asiankoala.koawalib.math.NVector
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import kotlin.math.*

class DriveCmd(
    private val drive: KMecanumDrive,
    private val leftStick: KStick,
    private val rightStick: KStick,
    private val rb: KButton
) : Cmd() {
    private val scalars: NVector
        get() = if(rb.isToggled) DriveConstants.slowScalars else DriveConstants.speedScalars

    private fun joystickFunction(s: Double, k: Double, x: Double): Double {
        return 2.6 * x * (k * abs(x).pow(3) - k / 5.0 + 1)
    }

    override fun execute() {
        val raws = NVector(
            leftStick.xSupplier.invoke(),
            -leftStick.ySupplier.invoke(),
            -rightStick.xSupplier.invoke()
        )

        drive.powers = raws
            .mapIndexed { i, d -> joystickFunction(scalars[i], 0.98, d) }
            .asPose
    }

    override val isFinished: Boolean
        get() = false

    init {
        addRequirements(drive)
    }
}