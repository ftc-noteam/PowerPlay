package asiankoala.ftc2022.oryx

import asiankoala.ftc2022.oryx.subsystems.*
import asiankoala.ftc2022.oryx.subsystems.constants.LiftConstants
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
    val drive = KMecanumDrive(
        MotorFactory("fl")
            .brake
            .reverse
            .build(),
        MotorFactory("bl")
            .brake
            .reverse
            .build(),

        MotorFactory("br")
            .brake
            .forward
            .build(),

        MotorFactory("fr")
            .brake
            .forward
            .build()
    )
    val lift = Lift()
    val claw = Claw()
    val pivot = Pivot()
    val arm = Arm()
    var state = State.INTAKING
    var strategy = Strategy.HIGH
    var isStacking = false
    var stackNum = 5
    val stackLiftHeight = LiftConstants.ready + stackNum * 3.0
}