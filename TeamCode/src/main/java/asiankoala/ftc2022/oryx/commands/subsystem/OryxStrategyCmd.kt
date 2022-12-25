package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.Oryx
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

class OryxStrategyCmd(oryx: Oryx, strategy: Strategy) : InstantCmd({ oryx.strategy = strategy })