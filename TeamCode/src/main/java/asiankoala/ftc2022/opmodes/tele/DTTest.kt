package asiankoala.ftc2022.opmodes.tele

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.NVector
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sign

class DTTest : KOpMode() {
    private lateinit var miyuki: Miyuki
    private var slowMode = true

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(Pose())
        miyuki.odometry.unregister()
        miyuki.vision.unregister()
        miyuki.lift.unregister()
        miyuki.arm.unregister()
        driver.leftStick.setDeadzone(0.12)
        driver.rightStick.setDeadzone(0.12)
    }

    override fun mStart() {
        miyuki.drive.defaultCommand = object : Cmd() {
            val fastScalars = NVector(0.8, 0.8, 0.3)
            val slowScalars = NVector(0.4, 0.4, 0.3)
            val scalars get() = if(slowMode) slowScalars else fastScalars

            private fun joystickFunction(s: Double, k: Double, x: Double): Double {
                return max(0.0, s * x * (k * x.pow(3) - k + 1)) * x.sign
            }

            override fun execute() {
                val raws = NVector(
                    -driver.leftStick.xSupplier.invoke(),
                    -driver.leftStick.ySupplier.invoke(),
                    -driver.rightStick.xSupplier.invoke()
                )

                miyuki.drive.powers = raws
                    .mapIndexed { i, d -> joystickFunction(scalars[i], 1.0, d) }
                    .asPose
            }

            init {
                addRequirements(miyuki.drive)
            }
        }

        driver.rightBumper.onPress(InstantCmd({ slowMode = !slowMode }))
    }

    override fun mLoop() {
        Logger.addTelemetryData("powers", miyuki.drive.powers)
        Logger.addTelemetryData("wheel powers", miyuki.drive.motors.map { it.power })
    }
}