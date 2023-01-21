package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.subsystems.*
import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive

class Sunmi(pose: Pose) {
//    val odo = KThreeWheelOdometry(
//        hw.leftEncoder,
//        hw.rightEncoder,
//        hw.auxEncoder,
//        OdoConstants.TRACK_WIDTH,
//        OdoConstants.PERP_TRACKER,
//        pose
//    )
//    val vision = Vision()
    val bl = MotorFactory("bl")
        .brake
        .reverse
        .build()
    val drive = KMecanumDrive(
        MotorFactory("fl")
            .brake
            .reverse
            .build(),
        bl,
        MotorFactory("br")
            .brake
            .forward
            .withStaticFeedforward(DriveConstants.brKStatic)
            .build(),

        MotorFactory("fr")
            .brake
            .forward
            .build()
    )
    val lift = Lift(bl)
    val claw = Claw()
    val pivot = Pivot()
    val arm = Arm()
    var state = State.INTAKING
    var strategy = Strategy.MED
    var isStacking = false
    var stackNum = 5
    val stackLiftHeight = LiftConstants.home + stackNum * 3.0
}