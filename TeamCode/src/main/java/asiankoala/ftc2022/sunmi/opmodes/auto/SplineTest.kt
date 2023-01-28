package asiankoala.ftc2022.sunmi.opmodes.auto

import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.DEFAULT_HEADING_CONTROLLER
import com.asiankoala.koawalib.path.HermitePath
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class SplineTest : KOpMode() {
    private val path = HermitePath(
        DEFAULT_HEADING_CONTROLLER,
        Pose(),
        Pose(24.0, 24.0, 90.0.radians)
    )

    fun getGVFCmd(
        sunmi: Sunmi,
        path: Path
    ) = GVFCmd(sunmi.drive, SimpleGVFController(path, sunmi.drive, SimpleGVFConstants.kN, SimpleGVFConstants.kOmega, SimpleGVFConstants.kF, SimpleGVFConstants.kS, SimpleGVFConstants.epsilon, SimpleGVFConstants.thetaEpsilon))

    override fun mInit() {
        val sunmi = Sunmi(Pose())
        sunmi.vision.unregister()

        + SequentialGroup(
            WaitUntilCmd({ opModeState == OpModeState.LOOP }),
            getGVFCmd(sunmi, path)
        )
    }
}
