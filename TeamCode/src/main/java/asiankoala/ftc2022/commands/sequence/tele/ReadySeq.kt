package asiankoala.ftc2022.commands.sequence.tele

import asiankoala.ftc2022.DepositState
import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.subsystem.PivotDepositCmd
import asiankoala.ftc2022.commands.subsystem.PivotLowCmd
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.logger.Logger

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