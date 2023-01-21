package asiankoala.ftc2022.sunmi.commands.sequence.tele

import asiankoala.ftc2022.sunmi.Sunmi
import com.asiankoala.koawalib.command.group.ParallelGroup

class Soyeon(sunmi: Sunmi) : ParallelGroup(
    sunmi.liftCmd,
    sunmi.armCmd,
    sunmi.pivotCmd
)