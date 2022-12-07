package asiankoala.ftc2022.commands.sequence.auto

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.ArmCmd
import asiankoala.ftc2022.commands.subsystem.ClawGripCmd
import asiankoala.ftc2022.commands.subsystem.ClawOpenCmd
import asiankoala.ftc2022.commands.subsystem.PivotDepositCmd
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.util.OpModeState

class PreInitCmd(miyuki: Miyuki, rightTriggerIsJustPressed: () -> Boolean) : SequentialGroup(
    ArmCmd(miyuki.arm, ArmConstants.autoInit)
        .waitUntil(rightTriggerIsJustPressed),
    ClawOpenCmd(miyuki.claw),
    ClawGripCmd(miyuki.claw)
        .waitUntil(rightTriggerIsJustPressed)
)
