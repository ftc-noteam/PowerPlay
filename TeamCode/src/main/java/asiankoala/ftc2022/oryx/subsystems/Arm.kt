package asiankoala.ftc2022.oryx.subsystems

import asiankoala.ftc2022.oryx.utils.AxonServoEncoder
import com.asiankoala.koawalib.control.profile.v2.Constraints
import com.asiankoala.koawalib.control.profile.v2.DispState
import com.asiankoala.koawalib.control.profile.v2.OnlineProfile
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.degrees
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.KSubsystem
import kotlin.math.absoluteValue

class Arm(
    private val left: KServo,
    private val right: KServo,
    private val enc: AxonServoEncoder,
    private val constraints: Constraints,
    private val epsilon: Double
) : KSubsystem() {
    private var profile: OnlineProfile? = null
    private var target = enc.angle
    private var setpoint = enc.angle
    val isAtTarget get() = (enc.angle - target).angleWrap.absoluteValue < epsilon
    val pos get() = enc.angle.degrees

    fun setTarget(pos: Double) {
        target = pos.radians
        profile = OnlineProfile(
            DispState(enc.angle),
            DispState(target),
            constraints
        )
    }

    override fun periodic() {
        enc.update()
        profile?.let {
            setpoint = if(!isAtTarget) it[enc.angle].x else target
        }
        left.position = setpoint
        right.position = setpoint
    }
}