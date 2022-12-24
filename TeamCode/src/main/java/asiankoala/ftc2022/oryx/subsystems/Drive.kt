package asiankoala.ftc2022.oryx.subsystems

import asiankoala.ftc2022.oryx.utils.State
import asiankoala.ftc2022.oryx.subsystems.constants.DriveConstants
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.math.NVector
import com.asiankoala.koawalib.subsystem.drive.KVelDrive
import com.asiankoala.koawalib.subsystem.odometry.Odometry

class Drive(
    fl: KMotor,
    bl: KMotor,
    br: KMotor,
    fr: KMotor,
    odometry: Odometry,
    private val state: () -> State
) : KVelDrive(
    fl, bl, br, fr, odometry,
    true,
    DriveConstants.kP,
    DriveConstants.kS,
    DriveConstants.kV,
    DriveConstants.kA,
    false
) {
    val scalar get() = when(state.invoke()) {
        State.INTAKING -> DriveConstants.intakeScalars
        State.READYING -> DriveConstants.speedScalars
        State.DEPOSITING -> DriveConstants.depositScalars
        State.HOMING -> DriveConstants.speedScalars
    }

    fun setVectors(v: NVector) {
        powers = (v * scalar).asPose
    }
}