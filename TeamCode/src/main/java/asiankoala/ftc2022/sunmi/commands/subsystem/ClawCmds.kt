package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

open class ClawCmd(claw: Claw, pos: Double) :
    InstantCmd({ claw.setTarget(pos) })
class ClawCloseCmd(claw: Claw) : ClawCmd(claw, ClawConstants.close)
class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.open)
class ClawStartReadingCmd(claw: Claw) : InstantCmd(claw::startReading)
class ClawStopReadingCmd(claw: Claw) : InstantCmd(claw::stopReading)