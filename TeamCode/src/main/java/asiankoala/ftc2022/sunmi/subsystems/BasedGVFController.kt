package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.subsystems.constants.SimpleGVFConstants
import com.asiankoala.koawalib.control.controller.PIDFController
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.degrees
import com.asiankoala.koawalib.path.Path
import com.asiankoala.koawalib.path.gvf.GVFController
import com.asiankoala.koawalib.path.gvf.Speeds
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.sign

class BasedGVFController(
    override val path: Path,
    override val drive: KMecanumOdoDrive,
    private val kN: Double,
    private val kOmega: Double,
    private val kF: Double,
    private val kS: Double,
    private val epsilon: Double,
    private val thetaEpsilon: Double,
    private val errorMap: (Double) -> Double = { it },
    private val epsilonToUsePID: Double = 4.0,
    private val pidControlEnabled: Boolean = false
) : GVFController {
    private var pose: Pose = Pose()
    private var headingError = 0.0

    override var s: Double = 0.0
    override val isFinished
        get() = path.length - s < epsilon &&
                pose.vec.dist(path.end.vec) < epsilon &&
                headingError.absoluteValue < thetaEpsilon

    private fun calcGVF(): Vector {
        Logger.addTelemetryLine("length - s: ${path.length - s}, dist: ${pose.vec.dist(path.end.vec)}, headingError: ${headingError.absoluteValue}")
        val tangent = path[s, 1].vec
        val normal = tangent.rotate(PI / 2.0)
        val displacementVec = path[s].vec - pose.vec
        val error = displacementVec.norm * (displacementVec cross tangent).sign
        return (tangent - normal * kN * errorMap.invoke(error)).unit
    }

    private fun headingControl(): Pair<Double, Double> {
        headingError = (path[s].heading - pose.heading).angleWrap.degrees
        val result = headingError / kOmega
        return Pair(result, headingError)
    }

    private fun vectorControl(v: Vector): Vector {
        return v * kS * min(1.0, (path.length - s) / kF)
    }

    private val xController = PIDFController(PIDGains(SimpleGVFConstants.kP, 0.0, SimpleGVFConstants.kD))
    private val yController = PIDFController(PIDGains(SimpleGVFConstants.kP, 0.0, SimpleGVFConstants.kD))

    override fun update() {
        pose = drive.pose
        s = path.project(pose.vec, s)
        val headingResult = headingControl()
        val vectorResult = if(pidControlEnabled && pose.vec.dist(path.end.vec) < epsilonToUsePID) {
            xController.targetPosition = path.end.vec.x
            yController.targetPosition = path.end.vec.y
            val xOutput = xController.update(pose.x)
            val yOutput = yController.update(pose.y)
            Logger.addTelemetryLine("USING PID CONTROL")
            Vector(xOutput, yOutput)
        } else {
            vectorControl(calcGVF())
        }
        val res = Speeds()
        res.setFieldCentric(Pose(vectorResult, headingResult.first))
        drive.powers = res.getRobotCentric(pose.heading)
    }

    init {
        require(kS <= 1.0)
    }
}
