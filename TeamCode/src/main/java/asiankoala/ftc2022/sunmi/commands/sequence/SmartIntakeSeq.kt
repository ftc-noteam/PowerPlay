package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.commands.subsystem.*
//import asiankoala.ftc2022.sunmi.commands.subsystem.ClawStartReadingCmd
//import asiankoala.ftc2022.sunmi.commands.subsystem.ClawStopReadingCmd
import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.subsystems.Lift
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.ClawConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.SeqConstants
import com.asiankoala.koawalib.command.commands.LoopUntilCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger

class SmartIntakeSeq(claw: Claw, arm: Arm, lift: Lift) : SequentialGroup(
    ClawStartReadingCmd(claw),
    WaitUntilCmd { claw.lastRead < ClawConstants.intakeThreshold },
    ClawStopReadingCmd(claw),
    ArmCmd(arm, ArmConstants.intake),
    WaitCmd(SeqConstants.afterArmDownDt),
    ClawCloseCmd(claw),
    WaitCmd(SeqConstants.afterClawGrabDt),
    LiftCmd(lift, 2.0),
    WaitCmd(SeqConstants.afterLiftRaiseDt),
    ArmCmd(arm, ArmConstants.home)
)
