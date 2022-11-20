package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive

class Miyuki(startPose: Pose) {
    val hardware = Hardware(startPose)

    val drive = KMecanumOdoDrive(
        hardware.fl,
        hardware.bl,
        hardware.fr,
        hardware.br,
        hardware.odometry,
        false
    )

//    val lift = Lift(hardware.liftLeadMotor, hardware.liftBottomMotor, hardware.liftLeftMotor)
//    val arm = Arm(hardware.armMotor)
    val pivot = Pivot(hardware.pivot)
    val claw = Claw(hardware.claw)
}
