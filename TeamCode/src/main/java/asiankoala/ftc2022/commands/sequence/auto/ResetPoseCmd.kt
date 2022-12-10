package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.commands.InstantCmd

class ResetPoseCmd(miyuki: Miyuki):
        InstantCmd(miyuki.hardware.odometry::reset)