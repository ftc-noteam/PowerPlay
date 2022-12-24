package asiankoala.ftc2022.miyuki.commands.sequence.auto

import asiankoala.ftc2022.miyuki.Miyuki
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.math.Pose

class ResetPoseCmd(miyuki: Miyuki, pose: Pose):
        InstantCmd({ miyuki.odometry.reset(pose) })