package asiankoala.ftc2022.opmodes.auto

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
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.FLIPPED_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.ProjQuery
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled

@Autonomous
@Disabled
class MiyukiTestAuto : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki
    private val startPose = Pose(AutoConstants.startPoseX, AutoConstants.startPoseY)
    private val kN = AutoConstants.kN
    private val kOmega = AutoConstants.kOmega
    private val kF = AutoConstants.kF
    private val kS = AutoConstants.kS
    private val epsilon = AutoConstants.epsilon
    private val thetaEpsilon = AutoConstants.thetaEpsilon

    private fun getGVFCmd(path: Path, vararg cmds: Pair<Cmd, ProjQuery>) =
        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)


    private val depositVec = Vector(AutoConstants.depositX, AutoConstants.depositY)
    private val intakeVec = Vector(AutoConstants.intakeX, AutoConstants.intakeY)

    private val initPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        startPose.copy(heading = 0.0),
        Pose(-24.0, -36.0, 0.0),
        Pose(depositVec, AutoConstants.initDepositHeadingDeg.radians)
    )

    private val initReadyProj = Vector(AutoConstants.initReadyProjX, AutoConstants.initReadyProjY)
    private val depositProj = Vector(AutoConstants.depositProjX, AutoConstants.depositProjY)

    private val intakePath = HermitePath(
        { 270.0.radians },
        Pose(depositVec, AutoConstants.depositToIntakeHeadingDeg.radians),
        Pose(intakeVec, 270.0.radians)
    )

    private val depositPath = HermitePath(
        { 45.0.radians },
        Pose(intakeVec, 90.0.radians),
        Pose(depositVec, AutoConstants.depositHeadingDeg.radians)
    )

    private val deltaLift = AutoConstants.deltaLift
    private val liftHeights = listOf(AutoConstants.liftHeight)
    private val intakeProj = Vector(AutoConstants.intakeProjX, AutoConstants.intakeProjY)
    private val readyProj = Vector(AutoConstants.readyProjX, AutoConstants.readyProjY)

    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        miyuki = Miyuki(startPose)

        val preInitCmd = SequentialGroup(
            ClawCmds.ClawGripCmd(miyuki.claw),
            ArmCmds.ArmCmd(miyuki.arm, ArmConstants.autoInit)
                .waitUntil(driver.rightTrigger::isJustPressed),
            PivotCmds.PivotDepositCmd(miyuki.pivot)
                .waitUntil { miyuki.arm.pos > 90.0 },
            WaitUntilCmd { opModeState == OpModeState.START }
        )

        + SequentialGroup(
            preInitCmd,
            getGVFCmd(
                initPath,
                Pair(AutoReadySeq(miyuki), ProjQuery(initReadyProj)),
                Pair(AutoDepositSeq(miyuki), ProjQuery(depositProj))
            ),
            *List(5) {
                listOf(
                    getGVFCmd(
                        intakePath,
                        Pair(
                            LiftCmd(miyuki.lift, liftHeights[it]),
                            ProjQuery(intakeProj)
                        )
                    ),
                    AutoIntakeSeq(miyuki, liftHeights[it] + deltaLift),
                    getGVFCmd(
                        depositPath,
                        Pair(
                            AutoReadySeq(miyuki),
                            ProjQuery(readyProj)
                        ),
                        Pair(
                            AutoDepositSeq(miyuki),
                            ProjQuery(depositProj)
                        )
                    ),
                )
            }.flatten().toTypedArray(),
        )

//        + SequentialGroup(
//            preInitCmd,
////            getGVFCmd(
////                initPath,
////                Pair(AutoReadySeq(miyuki), ProjQuery(initReadyVec)),
////                Pair(AutoDepositSeq(miyuki), ProjQuery(depositVec))
////            )
//        )
    }
}