package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive

class Miyuki(startPose: Pose) {
    private val hardware = Hardware(startPose)

//    val drive = KMecanumOdoDrive(
//        hardware.fl,
//        hardware.bl,
//        hardware.fr,
//        hardware.br,
//        hardware.odometry,
//        false
//    )

    val lift = Lift(hardware.liftLead, hardware.liftBottom, hardware.liftLeft)
    val arm = Arm(hardware.arm)
//    val pivot = Pivot(hardware.pivot)
//    val claw = Claw(hardware.claw)

    init {
        arm.motor.setPositionTarget(180.0)
    }
}
