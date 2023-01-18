package asiankoala.ftc2022.oryx.commands.subsystem

import asiankoala.ftc2022.oryx.Sunmi
import asiankoala.ftc2022.oryx.utils.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

class OryxStrategyCmd(sunmi: Sunmi, strategy: Strategy) : InstantCmd({ sunmi.strategy = strategy })