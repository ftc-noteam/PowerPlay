package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.math.Pose

class ResetPoseCmd(miyuki: Miyuki, pose: Pose):
        InstantCmd({ miyuki.odometry.reset(pose) })