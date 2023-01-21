package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Lift
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import asiankoala.ftc2022.sunmi.Strategy
import com.asiankoala.koawalib.command.commands.InstantCmd

open class LiftCmd(private val lift: Lift, private val pos: Double) :
    InstantCmd({ lift.setTarget(pos) })
