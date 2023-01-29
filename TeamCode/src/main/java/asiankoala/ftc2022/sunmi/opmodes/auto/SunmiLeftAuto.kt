package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.auto.*
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class SunmiLeftAuto : SunmiRightAuto() {
    override val autoPaths by lazy { leftAutoPaths }
    override val startPose by lazy { flipPose(rightSideRobotStartPose) }
}
