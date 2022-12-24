package asiankoala.ftc2022.miyuki.commands.sequence.tele

import asiankoala.ftc2022.miyuki.DepositState
import asiankoala.ftc2022.miyuki.Miyuki
import asiankoala.ftc2022.miyuki.commands.subsystem.PivotDepositCmd
import asiankoala.ftc2022.miyuki.commands.subsystem.PivotLowCmd
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class ReadySeq(
    miyuki: Miyuki,
) : ParallelGroup(
    miyuki.liftCmd,
    miyuki.armCmd,
    WaitUntilCmd { miyuki.arm.pos > 0.0 || miyuki.strategy == DepositState.LOW }
        .andThen(
            ChooseCmd(
                PivotLowCmd(miyuki.pivot),
                PivotDepositCmd(miyuki.pivot)
            ) { miyuki.strategy == DepositState.LOW },
        )
)