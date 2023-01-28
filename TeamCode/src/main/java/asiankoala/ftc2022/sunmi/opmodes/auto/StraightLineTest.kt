package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.epsilon
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.kF
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.kN
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.kOmega
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.kS
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants.thetaEpsilon
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class StraightLineTest : KOpMode() {
    private val path = HermitePath(
        { t, s -> 0.0 },
        Pose(),
        Pose(24.0)
    )

    fun getGVFCmd(
        sunmi: Sunmi,
        path: Path
    ) = GVFCmd(sunmi.drive, SimpleGVFController(path, sunmi.drive, kN, kOmega, kF, kS, epsilon, thetaEpsilon))

    override fun mInit() {
        val sunmi = Sunmi(Pose())
        sunmi.vision.unregister()

        + SequentialGroup(
            WaitUntilCmd({ opModeState == OpModeState.LOOP }),
            getGVFCmd(sunmi, path)
        )
    }
}