package asiankoala.ftc2022.commands.subsystem

import asiankoala.ftc2022.L9SpacegliderScript1v9TurboBoostHack
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.gamepad.functionality.Stick
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.util.Alliance

class L9SpacegliderScript1v9TurboBoostHackCmd(
    drive: KMecanumOdoDrive,
    leftStick: Stick,
    rightStick: Stick,
    private val spacegliderToggle: () -> Boolean,
    private val aimbotToggle: () -> Boolean,
    alliance: Alliance = Alliance.BLUE,
) : MecanumCmd(
    drive,
    leftStick,
    rightStick,
    scalars.first,
    scalars.second,
    scalars.third,
    cubics.first,
    cubics.second,
    cubics.third,
    alliance,
    isTranslationFieldCentric = true,
    isHeadingFieldCentric = true,
    { drive.pose.heading },
    hs
) {
    private val l9SpacegliderScript1v9TurboBoostHack = L9SpacegliderScript1v9TurboBoostHack(
        drive::pose,
        R,
        N,
        hs
    )

    override fun processPowers(): Pose {
        val default = super.processPowers()
        val v = if (spacegliderToggle.invoke()) {
            l9SpacegliderScript1v9TurboBoostHack.spaceglide(default.vec)
        } else default.vec

        val h = if (aimbotToggle.invoke()) {
            l9SpacegliderScript1v9TurboBoostHack.aimbot(default.vec)
        } else default.heading

        return Pose(v, h)
    }

    companion object {
        private val scalars = Triple(1.0, 1.0, 1.0)
        private val cubics = Triple(1.0, 1.0, 1.0)
        private const val R = 2.0
        private const val N = 1.0
        private val hs = 60.0.radians
    }
}