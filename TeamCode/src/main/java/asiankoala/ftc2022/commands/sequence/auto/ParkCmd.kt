package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.Zones
import asiankoala.ftc2022.opmodes.auto.AutoConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class ParkCmd(miyuki: Miyuki, zoneP: () -> Zones) : SequentialGroup(
    when(zoneP.invoke()) {
        Zones.LEFT -> AutoConstants.getGVFCmd(miyuki, AutoConstants.leftPath)
        Zones.MIDDLE -> AutoConstants.getGVFCmd(miyuki, AutoConstants.middlePath)
        Zones.RIGHT -> AutoConstants.getGVFCmd(miyuki, AutoConstants.rightPath)
        else -> WaitCmd(0.5)
    }
)