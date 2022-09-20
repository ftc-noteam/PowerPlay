package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Strategy
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd

class MiyukiTeleOp : KOpMode() {
    private val miyuki by lazy { Miyuki() }
    private val strategy = Strategy(miyuki.lift, miyuki.arm, miyuki.pivot)

    override fun mInit() {
        miyuki.odometry.unregister()
        + InstantCmd({ driver.rumble(2) }).waitUntil {  time > 115 } // 5 sec before endgame
    }

    private fun bindDrive() {
        miyuki.drive.setDefaultCommand(
            MecanumCmd(
                miyuki.drive,
                driver.leftStick,
                driver.rightStick,
            )
        )
    }

    private fun bindCycle() {

    }

    private fun bindStrategy() {

    }
}