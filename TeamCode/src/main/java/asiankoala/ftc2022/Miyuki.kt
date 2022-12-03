package asiankoala.ftc2022

import asiankoala.ftc2022.subsystems.*
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive

class Miyuki(startPose: Pose) {
    val hardware = Hardware(startPose)

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
    val pivot = Pivot(hardware.pivot)
    val claw = Claw(hardware.claw)

    init {
//        arm.setPos(ArmConstants.home)
        arm.setPos(-45.0)
    }
}
