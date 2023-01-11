package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.subsystems.Retract
import com.asiankoala.koawalib.command.commands.InstantCmd

class RetractCmd(retract: Retract) : InstantCmd(retract::retract, retract)
