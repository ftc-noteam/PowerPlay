package asiankoala.ftc2022.oryx.opmodes

import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Left")
class OryxLeftAuto : OryxRightAuto() {
    private fun flip(pose: Pose) = pose.copy(pose.x, -pose.y, -pose.heading)
    override val start = flip(super.start)
    override val initReady = flip(super.initReady)
    override val deposit = flip(super.deposit)
    override val intake = flip(super.intake)
}