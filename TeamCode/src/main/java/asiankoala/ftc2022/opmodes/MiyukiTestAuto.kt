package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import asiankoala.ftc2022.commands.sequence.auto.AutoDepositSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoIntakeSeq
import asiankoala.ftc2022.commands.sequence.auto.AutoReadySeq
import asiankoala.ftc2022.commands.subsystem.ArmCmds
import asiankoala.ftc2022.commands.subsystem.ClawCmds
import asiankoala.ftc2022.commands.subsystem.LiftCmds.LiftCmd
import asiankoala.ftc2022.commands.subsystem.PivotCmds
import asiankoala.ftc2022.subsystems.constants.ArmConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class MiyukiTestAuto : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki
    private val startPose = Pose(-66.0, -36.0, 180.0.radians)
    private val kN = 0.6
    private val kOmega = 30.0
    private val kF = 4.0
    private val kS = 1.0
    private val epsilon = 2.0
    private val thetaEpsilon = 5.0

    private fun getGVFCmd(path: Path, vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)

    private val preInitCmd = SequentialGroup(
        ClawCmds.ClawGripCmd(miyuki.claw),
        ArmCmds.ArmCmd(miyuki.arm, ArmConstants.autoInit)
            .waitUntil(driver.rightTrigger::isJustPressed),
        PivotCmds.PivotDepositCmd(miyuki.pivot)
            .waitUntil { miyuki.arm.pos > 90.0 },
    )

    private val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        Pose(-11.0, -41.5, 65.0.radians)
    )

    // fix
    private val initReadyVec = Vector(-17.0, -36.0)
    private val depositVec = Vector(-8.0, -33.0)

    private val intakePath = HermitePath(
        { 270.0.radians },
        Pose(-8.0, -32.0, 250.0.radians),
        Pose(-12.0, -65.0, 270.0.radians)
    )

    private val depositPath = HermitePath(
        { 45.0.radians },
        Pose(-12.0, -65.0, 90.0.radians),
        Pose(-8.0, -32.0, 60.0.radians)
    )

    private val deltaLift = 4.0
    private val liftHeights = listOf(6.0)
    private val intakeReadyVec = Vector(-12.0, -45.0)

    private val readyVec = Vector(-12.0, -40.0)

    override fun mInit() {
        + SequentialGroup(
            preInitCmd,
            getGVFCmd(
                initPath,
                Pair(AutoReadySeq(miyuki), ProjQuery(initReadyVec)),
                Pair(AutoDepositSeq(miyuki), ProjQuery(depositVec))
            ),
            *List(5) {
                listOf(
                    getGVFCmd(
                        intakePath,
                        Pair(
                            LiftCmd(miyuki.lift, liftHeights[it]),
                            ProjQuery(intakeReadyVec)
                        )
                    ),
                    AutoIntakeSeq(miyuki, liftHeights[it] + deltaLift),
                    getGVFCmd(
                        depositPath,
                        Pair(
                            AutoReadySeq(miyuki),
                            ProjQuery(readyVec)
                        ),
                        Pair(
                            AutoDepositSeq(miyuki),
                            ProjQuery(depositVec)
                        )
                    ),
                )
            }.flatten().toTypedArray(),
        )
    }
}