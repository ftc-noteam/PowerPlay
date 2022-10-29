package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.CmdChooser
import asiankoala.ftc2022.commands.sequence.DepositSequence
import asiankoala.ftc2022.commands.sequence.IntakeSequence
import asiankoala.ftc2022.commands.sequence.ReadySequence
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.CubicPath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.ReversedCubicPath
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState

open class MiyukiAuto(startPose: Pose) : KOpMode() {
    private val miyuki by lazy { Miyuki(startPose) }
    private val kN = 0.6
    private val kOmega = 1.0 / 30.0.radians
    private val kF = 4.0
    private val kS = 1.0
    private val epsilon = 1.0

    private fun defaultGVFCmd(path: Path, vararg cmds: Pair<Cmd, Vector>): GVFCmd {
        return GVFCmd(
            miyuki.drive,
            SimpleGVFController(path, kN, kOmega, kF, kS, epsilon),
            *cmds
        )
    }

    override fun mInit() {
        val intakePath = CubicPath(
            Pose(-3.0, -28.0, 210.0.radians),
            Pose(-12.0, -66.0, 270.0.radians)
        )

        val depositPath = ReversedCubicPath(
            Pose(-12.0, -66.0, 90.0.radians),
            Pose(-3.0, -28.0, 30.0.radians)
        )

        val intakeP = Pair(IntakeSequence(miyuki.claw), Vector(-12.0, -43.0))
        val readyP = Pair(ReadySequence(miyuki), Vector(-12.0, -36.0))
        val depositP = Pair(DepositSequence(miyuki), Vector(-5.0, -30.0))

        +SequentialGroup(
            ClawCmds.ClawGripCmd(miyuki.claw),
            CmdChooser.homeCmd(miyuki),
            WaitUntilCmd { opModeState == OpModeState.START },
            defaultGVFCmd(
                ReversedCubicPath(
                    Pose(-66.0, -36.0, 0.0),
                    Pose(-3.0, -28.0, 30.0.radians)
                ),
                Pair(ReadySequence(miyuki), Vector(-24.0, -36.0)),
                depositP
            ),
            *List(5) {
                listOf(
                    defaultGVFCmd(intakePath, intakeP),
                    defaultGVFCmd(depositPath, readyP, depositP)
                )
            }.flatten().toTypedArray()
        )
    }
}