package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

class SunmiStratCmd(sunmi: Sunmi, strategy: Strategy) : InstantCmd({ sunmi.strategy = strategy })